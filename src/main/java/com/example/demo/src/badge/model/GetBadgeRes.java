package com.example.demo.src.badge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetBadgeRes {

    private int userId;
    private boolean startDeal;
    private boolean townPioneer;
    private boolean startItem;
    private boolean firstRecommend;
    private boolean startWarm;

}
