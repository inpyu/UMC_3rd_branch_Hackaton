package com.example.demo.src.townImg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTownImgRes {

    private int id;
    private String storedUrl;
    private int townPosterId;
    private String originalFileName;

}
