package com.cb.sessionstore.service;

import com.cb.sessionstore.controller.SessionCart;
import com.cb.sessionstore.model.CartItem;
import com.cb.sessionstore.model.vo.ProductCount;
import com.cb.sessionstore.model.vo.SessionDoc;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private Bucket bucket;

    @Override
    public Long countSessions() {
        N1qlQuery countQuery = N1qlQuery.simple("SELECT COUNT(*) AS size FROM "+bucket.name());
        N1qlQueryResult result = bucket.query(countQuery);
        List<N1qlQueryRow> rows = result.allRows();
        return rows.get(0).value().getLong("size");
    }

    @Override
    public List<ProductCount> listMostCommonProducts() {

        N1qlQuery query = N1qlQuery.simple( "SELECT i.itemName as itemName, SUM(i.quantity) AS totalQuantity "
                +" FROM "+bucket.name()+" s UNNEST DECODE_JSON(s.sessionCart).shoppingCart.items i "
                +" WHERE s.sessionCart IS NOT MISSING "
                + "GROUP BY i.itemName ORDER BY SUM(i.quantity) DESC LIMIT 10");

        N1qlQueryResult result = bucket.query(query);
        List<ProductCount> list = new ArrayList<>();
        for (N1qlQueryRow row: result.allRows()) {
            list.add( new ProductCount(row.value().getString("itemName"),
                    row.value().getLong("totalQuantity")));
        }
        return list;
    }

    @Override
    public List<SessionDoc> list10MostRecent() throws Exception {

        N1qlQuery query = N1qlQuery.simple( "SELECT meta().id as id, _created, sessionCart FROM "+bucket.name()
                + " order by _created desc limit 10");

        N1qlQueryResult result = bucket.query(query);
        List<SessionDoc> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (N1qlQueryRow row: result.allRows()) {
            SessionCart sessionCart = mapper.readValue(row.value().getString("sessionCart"), SessionCart.class);

            list.add( new SessionDoc(row.value().getString("id"),
                    row.value().getLong("_created"),
                    sessionCart.getShoppingCart() !=null?sessionCart.getShoppingCart().getItems().size():0));
        }
        return list;
    }

}
