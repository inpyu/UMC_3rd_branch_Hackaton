package com.example.demo.src.town.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Town {

    private int id;
    private int userId;
    private String content;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

}
