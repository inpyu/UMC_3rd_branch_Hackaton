package com.example.demo.src.town;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.town.model.GetTownReq;
import com.example.demo.src.town.model.GetTownRes;
import com.example.demo.src.town.model.PostTownRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poster/town")
@RequiredArgsConstructor
public class TownController {

    @Autowired
    private final TownService townService;

    /**
     * 동네 생활 게시글 작성 API
     * [POST]
     */
    // Body
    @ResponseBody
    @PostMapping("")    // POST 방식의 요청을 매핑하기 위한 어노테이션
    public BaseResponse<PostTownRes> createPostTown(@RequestBody GetTownReq getTownReq) {

        try {
            PostTownRes posttownRes = townService.createTownPost(getTownReq);
            return new BaseResponse<>(posttownRes);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
