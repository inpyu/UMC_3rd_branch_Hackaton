package com.example.demo.src.purchase;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.purchase.model.PostPurchaseReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    @PostMapping("/purchases")
    public BaseResponse<String> purchase(@RequestBody PostPurchaseReq postPurchaseReq) {

        try {
            int purchaseId = purchaseService.purchase(postPurchaseReq);
            return new BaseResponse<>("생성되었습니다: "+purchaseId);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
