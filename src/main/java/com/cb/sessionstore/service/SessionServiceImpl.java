package com.cb.sessionstore.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
