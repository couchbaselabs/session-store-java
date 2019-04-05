package com.cb.sessionstore.controller;


import com.cb.sessionstore.model.Foo;
import com.cb.sessionstore.model.vo.ProductCount;
import com.cb.sessionstore.model.vo.SessionDoc;
import com.cb.sessionstore.service.SessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SessionService sessionService;
    private ObjectMapper mapper = new ObjectMapper();


    private final String SESSION_CART= "sessionCart";
    private final String FOO= "foo";

    @GetMapping("/")
    public String main(Model model, HttpSession session) throws Exception {
        return defaultPage(model, session);
    }

    @GetMapping("/newSession")
    public String mainWithParam(HttpServletRequest request, Model model) throws Exception {

        request.getSession().invalidate();
        HttpSession newSession = request.getSession();
        //Foo will be serialized in the database inside the session entity
        newSession.setAttribute(FOO, new Foo("value1", "value2"));
        putSessionCart(getSessionCart(newSession), newSession);
        return defaultPage(model, newSession);
    }

    @GetMapping("/AddShoppingCartDataToSession")
    public String addRandomShoppingCartToSession( Model model, HttpSession session) throws Exception {
        SessionCart sc = getSessionCart(session);
        sc.setShoppingCart(DataGenerator.getRandomCart());
        putSessionCart(sc, session);
        return defaultPage(model, session);
    }

    @GetMapping("/ClearShoppingCartDataInSession")
    public String clearShoppingCartToSession( Model model, HttpSession session) throws Exception {
        SessionCart sc = getSessionCart(session);
        sc.setShoppingCart(null);
        putSessionCart(sc, session);

        return defaultPage(model, session);
    }




    @GetMapping("/AddUserDataToSession")
    public String addUserDataToSession(Model model, HttpSession session) throws Exception {
        SessionCart sc = getSessionCart(session);
        sc.setUser(DataGenerator.getRandomUser());
        putSessionCart(sc, session);

        return defaultPage(model, session);
    }

    @GetMapping("/ClearUserDataInSession")
    public String clearUserDataInSession(Model model, HttpSession session) throws Exception {
        SessionCart sc = getSessionCart(session);
        sc.setUser(null);
        putSessionCart(sc, session);
        return defaultPage(model, session);
    }




    @GetMapping("/AddLocationDataToSession")
    public String addLocationDataToSession( Model model, HttpSession session) throws Exception {
        SessionCart sc = getSessionCart(session);
        sc.setLocation(DataGenerator.getRandoLocation());
        putSessionCart(sc, session);

        return defaultPage(model, session);
    }

    @GetMapping("/ClearLocationDataInSession")
    public String clearLocationDataInSession( Model model, HttpSession session) throws Exception {
        SessionCart sc = getSessionCart(session);
        sc.setLocation(null);
        putSessionCart(sc, session);
        return defaultPage(model, session);
    }



    @GetMapping("/ReportRecentShoppingCarts")
    public String recentShopingCarts( Model model, HttpSession session) throws Exception {
        List<SessionDoc> sessions = sessionService.list10MostRecent();
        model.addAttribute("sessions", sessions);
        return "recentShoppingCarts";
    }

    @GetMapping("/ReportMostCommonShoppingCartItems")
    public String commonShoppingItems( Model model, HttpSession session) throws Exception {
        List<ProductCount> items = sessionService.listMostCommonProducts();
        model.addAttribute("products", items);
        return "commonShoppingItems";
    }


    private String defaultPage(Model model,  HttpSession session) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            model.addAttribute("jsonSessionCart", mapper.writeValueAsString(getSessionCart(session)));
        } catch (JsonProcessingException e) {
            model.addAttribute("jsonSessionCart", "{}");
        }
        model.addAttribute("sessionCount", sessionService.countSessions());
        return "welcome";
    }


    private SessionCart getSessionCart(HttpSession session) throws IOException {
        if(session.getAttribute(SESSION_CART) != null) {
            return mapper.readValue( session.getAttribute(SESSION_CART).toString(), SessionCart.class);
        } else {
            return new SessionCart();
        }
    }

    private void putSessionCart(SessionCart cart, HttpSession session) throws JsonProcessingException {
        session.setAttribute(SESSION_CART, mapper.writeValueAsString(cart));
    }


}
