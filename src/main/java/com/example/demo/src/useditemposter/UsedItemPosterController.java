package com.example.demo.src.useditemposter;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.useditemposter.model.GetUsedItemPosterRes;
import com.example.demo.src.useditemposter.model.PatchUsedItemPosterReq;
import com.example.demo.src.useditemposter.model.PostUsedItemPosterReq;
import com.example.demo.src.useditemposter.model.PostUsedItemPosterRes;
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
                                                                       @RequestParam(required = false) String searchWord,
                                                                       @RequestParam int page) {
        //validation

        List<GetUsedItemPosterRes> res;
        try {
            if(searchWord != null) {
                res = usedItemPosterProvider.getUsedItemPostersByWord(searchWord);
            } else if (categoryId != null && userId != null) {
                return new BaseResponse<>(REQUEST_ERROR);
            } else if(categoryId == null && userId == null) {
                res = usedItemPosterProvider.getUsedItemPosters(page);
            } else if (categoryId != null) {
                res = usedItemPosterProvider.getUsedItemPostersByCategoryId(categoryId.intValue(), page);
            } else {
                return new BaseResponse<>(NOT_IMPLEMENT);
            }

        }catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        if(res.size() == 0) {
            return new BaseResponse<>(NO_CORRESPONDING_DATA);
        }
        return new BaseResponse<>(res);
    }

    /**
     * 중고거래 글을 수정한다.
     * @param postId
     * @param patchUsedItemPosterReq
     * @return
     */
    @PatchMapping("/{postId}")
    public BaseResponse<String> modifyUsedItemPoster(@PathVariable int postId, @RequestBody PatchUsedItemPosterReq patchUsedItemPosterReq) {
        try {
            usedItemPosterService.modifyUsedItemPoster(postId,patchUsedItemPosterReq);
            return new BaseResponse<>("정상적으로 변경되었습니다.");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 중고거래 글을 삭제한다.
     * @param postId
     * @return
     */
    @DeleteMapping("/{postId}")
    public BaseResponse<String> deleteUsedItemPoster(@PathVariable int postId) {
        try {
            usedItemPosterService.deleteUsedItemPoster(postId);
            return new BaseResponse<>("정상적으로 삭제되었습니다.");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }


    @PatchMapping("/{postId}/heart-clicked")
    public BaseResponse<String> heartClicked(@PathVariable int postId) {
        try {
            usedItemPosterService.raisePosterHeart(postId);
            return new BaseResponse<>("정상적으로 증가되었습니다.");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    /**
     *
     * @param request
     * @return
     *
     * 중고거래 글을 생성한다.
     */
    @PostMapping
    public BaseResponse<PostUsedItemPosterRes> createUsedItemPoster(@RequestBody PostUsedItemPosterReq request) {

        try {
            validatePostUsedItemPosterReq(request);
            PostUsedItemPosterRes res = usedItemPosterService.createUsedItemPoster(request);
            return new BaseResponse<>(res);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    private void validatePostUsedItemPosterReq(PostUsedItemPosterReq request) throws BaseException {
        int sellerId = request.getSellerId();
        String content = request.getContent();
        int categoryId = request.getCategoryId();
        String title = request.getTitle();
        int price = request.getPrice();

        if(sellerId < 1) {
            throw new BaseException(REQUEST_ERROR);
        }

        if( content.length() == 0 || content.length() > 1000) {
            throw new BaseException(REQUEST_ERROR);
        }

        if(price < 0) {
            throw new BaseException(REQUEST_ERROR);
        }

        if(categoryId < 1) {
            throw new BaseException(REQUEST_ERROR);
        }

        if(title.isEmpty() || title.length() > 100) {
            throw new BaseException(REQUEST_ERROR);
        }
    }


}
