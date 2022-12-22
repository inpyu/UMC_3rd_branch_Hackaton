package com.example.demo.src.useditemposter.model;

import lombok.Builder;
import lombok.Data;

@Data
public class PostUsedItemPosterReq {
    private int sellerId;
    private int categoryId;
    private String title;
    private int price;
    private String content;

    @Builder
    public PostUsedItemPosterReq(int sellerId, int categoryId, String title, int price, String content) {
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.title = title;
        this.price = price;
        this.content = content;
    }
}
