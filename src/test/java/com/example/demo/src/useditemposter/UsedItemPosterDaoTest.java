package com.example.demo.src.useditemposter;

import com.example.demo.src.useditemposter.model.GetUsedItemPosterRes;
import com.example.demo.src.useditemposter.model.PostUsedItemPosterReq;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class UsedItemPosterDaoTest {

    @Autowired
    private UsedItemPosterDao dao;


    @Test
    void getUsedItemPoster() {
        GetUsedItemPosterRes usedItemPoster = dao.getUsedItemPoster(1);

        assertThat(usedItemPoster.getTitle()).isEqualTo("아이폰 32 팝니다.");
    }

    @Test
    void createUsedItemPoster() {
        PostUsedItemPosterReq req = PostUsedItemPosterReq.builder().sellerId(1).categoryId(1).content("테스트용 내용").title("테스트용 제목")
                .price(10000).build();
        int returnIdx = dao.createUsedItemPoster(req);

        GetUsedItemPosterRes usedItemPoster = dao.getUsedItemPoster(returnIdx);
        assertThat(usedItemPoster.getContent()).isEqualTo("테스트용 내용");
    }

    @Test
    @DisplayName("카테고리별 가져오기")
    void category() {
        List<GetUsedItemPosterRes> byCategory = dao.findByCategoryId(1, 10, 0);

        System.out.println("ByCategory.size() == "+byCategory.size());
        byCategory.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("모두 가져오기")
    void all() {
        List<GetUsedItemPosterRes> all = dao.findAll(10, 0);

        all.stream().forEach(System.out::println);
    }
}