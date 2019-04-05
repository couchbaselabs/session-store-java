package com.cb.sessionstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foo implements Serializable {

    private String attribute1;
    private String attribute2;
}

