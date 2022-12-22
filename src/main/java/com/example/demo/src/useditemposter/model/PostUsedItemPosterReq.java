package com.example.demo.src.useditemposter.model;

import lombok.Data;

@Data
public class PostUsedItemPosterReq {
    private int sellerId;
    private int categoryId;
    private String title;
    private int price;
    private String content;
}
