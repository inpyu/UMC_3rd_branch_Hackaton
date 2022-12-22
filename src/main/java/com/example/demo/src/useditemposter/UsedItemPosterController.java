package com.example.demo.src.useditemposter;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.useditemposter.model.GetUsedItemPosterRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/posters/useditem")
@RequiredArgsConstructor
public class UsedItemPosterController {

    private final UsedItemPosterProvider usedItemPosterProvider;
    private final UsedItemPosterService usedItemPosterService;

    /**
     *  중고거래 글 1개 조회
     * [GET] /poster/useditem/{postId}
     * @return BaseResponse<GetUsedItemPosterRes>
     */
    @GetMapping("/{postId}")
    public BaseResponse<GetUsedItemPosterRes> getUsedItemPoster(@PathVariable int postId) {

        try {
            GetUsedItemPosterRes res = usedItemPosterProvider.getUsedItemPosterById(postId);
            return new BaseResponse<>(res);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     *
     * @param categoryId
     * @param userId
     * @param page 0부터 들어올 수 있음
     *             0PAGE = DATA 0 ~ 9 , 1PAGE = DATA 10~19
     * @return
     */
    @GetMapping
    public BaseResponse<List<GetUsedItemPosterRes>> getUsedItemPosters(@RequestParam(required = false) Integer categoryId,
                                                                       @RequestParam(required = false) Integer userId,
                                                                       @RequestParam int page) {
        //validation
        try {

            if ((categoryId == null && userId == null) || (categoryId != null && userId != null)) {
                return new BaseResponse<>(REQUEST_ERROR);
            } else {
                if (categoryId != null) {
                    List<GetUsedItemPosterRes> res = usedItemPosterProvider.getUsedItemPostersByCategoryId(categoryId.intValue(), page);

                    if(res.size() == 0) {
                        return new BaseResponse<>(NO_CORRESPONDING_DATA);
                    }
                    return new BaseResponse<>(res);

                } else {
                    return new BaseResponse<>(NOT_IMPLEMENT);
                }
            }
        }catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
