package com.example.demo.src.town.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTownImgReq {

    private String storedUrl;
    private int townPosterId;
    private String originalFileName;

}
