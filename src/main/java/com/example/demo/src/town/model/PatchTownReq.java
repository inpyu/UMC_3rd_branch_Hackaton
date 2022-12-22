package com.example.demo.src.town.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class PatchTownReq {

    private String content;
    private Timestamp modifiedAt;
    private int id;

}
