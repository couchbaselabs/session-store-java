package com.cb.sessionstore.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDoc implements Serializable {

    private String documentId;
    private long createdDate;
    private long items;
}
