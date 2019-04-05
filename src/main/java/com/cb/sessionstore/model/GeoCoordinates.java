package com.cb.sessionstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoCoordinates implements Serializable {

    private Long lat;
    private Long lon;
}
