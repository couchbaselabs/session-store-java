package com.cb.sessionstore.controller;

import com.cb.sessionstore.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataGenerator {


    private static String[] POSSIBLE_SHOPPING_CART_ITEMS = { "Socks", "T-Shirt", "Hat", "Tennis Shoes", "Scarf", "Gloves", "Necklace", "Watch" };
    private static String[] POSSIBLE_USERNAMES = { "johnsmith", "tomjones", "robertst", "benjaminsg", "matthewgv", "jamesls", "joshuamd", "ryanet" };


    private static String[] POSSIBLE_ADDRESSES = {
            "9040 East Princess St Wappingers Falls, NY 12590",
            "9286 Sunnyslope Road Dearborn Heights, MI 48127",
            "693 Orchard Avenue Malvern, PA 19355",
            "90 Arrowhead Avenue Jonesboro, GA 30236",
            "9149 Marsh Ave. Ringgold, GA 30736",
            "56 South Pearl Ave. Victoria, TX 77904"
    };

    public static String getRandomPhoneNumber() {
        int num1, num2, num3; //3 numbers in area code
        int set2, set3; //sequence 2 and 3 of the phone number

        Random generator = new Random();
        //Area code number; Will not print 8 or 9
        num1 = generator.nextInt(7) + 1; //add 1 so there is no 0 to begin
        num2 = generator.nextInt(8); //randomize to 8 becuase 0 counts as a number in the generator
        num3 = generator.nextInt(8);
        set2 = generator.nextInt(643) + 100;
        set3 = generator.nextInt(8999) + 1000;

        return "(" + num1 + "" + num2 + "" + num3 + ")" + "-" + set2 + "-" + set3;
    }


    public static Location getRandoLocation() {
        Random r = new Random();
        Location location = new Location();
        location.setAddress(POSSIBLE_ADDRESSES[r.ints(0, 5).findFirst().getAsInt()]);
        location.setCountry("USA");
        location.setCoordinates(getRandoCoordinates());
        return location;
    }


    public static GeoCoordinates getRandoCoordinates() {
        Random r = new Random();
        return new GeoCoordinates(r.longs(0, 200).findFirst().getAsLong(),
                r.longs(0, 200).findFirst().getAsLong());
    }


    public static Cart getRandomCart() {

        List<CartItem> items = new ArrayList<>();
        Random r = new Random();
        int size = r.ints(1, 3).findFirst().getAsInt();
        for(int i=0; i <size; i++) {

            items.add(new CartItem(POSSIBLE_SHOPPING_CART_ITEMS[r.ints(0, 7).findFirst().getAsInt()],
                    r.doubles(0.99, 49.99).findFirst().getAsDouble(),
                    r.ints(1, 5).findFirst().getAsInt()));
        }

        return new Cart(new Date().getTime(), items);
    }

    public static User getRandomUser() {
        Random r = new Random();
        return new User(POSSIBLE_USERNAMES[r.ints(0, 7).findFirst().getAsInt()], DataGenerator.getRandomPhoneNumber());
    }
}
