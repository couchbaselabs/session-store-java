package com.cb.sessionstore.service;

import com.cb.sessionstore.model.vo.ProductCount;
import com.cb.sessionstore.model.vo.SessionDoc;

import java.util.List;

public interface SessionService {

    Long countSessions();

    List<ProductCount> listMostCommonProducts();

    List<SessionDoc> list10MostRecent() throws Exception;
}
