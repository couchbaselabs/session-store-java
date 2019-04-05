package com.cb.sessionstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable {

    private String address;
    private String country;
    private GeoCoordinates coordinates;
}
