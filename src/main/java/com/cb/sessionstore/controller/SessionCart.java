package com.cb.sessionstore.controller;

import com.cb.sessionstore.model.Cart;
import com.cb.sessionstore.model.Location;
import com.cb.sessionstore.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class SessionCart implements Serializable {

    private Cart shoppingCart;
    private User user;
    private Location location;
}
