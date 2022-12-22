package com.example.demo.src.useditemposter.model;

import com.example.demo.utils.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class GetUsedItemPosterRes extends BaseTimeEntity {
    public GetUsedItemPosterRes(int id, String categoryName, String title, int price, String status, String content, int hearts,
                                Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.categoryName = categoryName;
        this.title = title;
        this.price = price;
        this.status = status;
        this.content = content;
        this.hearts = hearts;
        super.setCreatedAt(createdAt);
        super.setModifiedAt(modifiedAt);
    }

    private int id;

    //private User seller;

    private String categoryName;

    private String title;

    private int price;

    private String status;

    private String content;

    private int hearts;


}
