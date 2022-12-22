package com.example.demo.src.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostPurchaseReq {
    private int buyerId;
    private int usedItemPosterId;
}
