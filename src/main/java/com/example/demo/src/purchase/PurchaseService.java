package com.example.demo.src.purchase;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.purchase.model.PostPurchaseReq;
import com.example.demo.src.useditemposter.UsedItemPosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseDao purchaseDao;
    private final UsedItemPosterService usedItemPosterService;

    @Transactional
    public int purchase(PostPurchaseReq postPurchaseReq) throws BaseException {
        try {
            int purchasedId = purchaseDao.purchase(postPurchaseReq);
            usedItemPosterService.setPosterStatusPurchased(postPurchaseReq.getUsedItemPosterId());


            return purchasedId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }

    }
}
