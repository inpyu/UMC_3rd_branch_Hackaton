package com.example.demo.src.town;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.profile.model.GetProfileRes;
import com.example.demo.src.town.model.*;
import com.example.demo.src.user.model.GetUserRes;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posters/town")
@RequiredArgsConstructor
public class TownController {

    @Autowired
    private final TownService townService;
    @Autowired
    private final TownProvider townProvider;

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

    /**
     * 동네 생활 게시글 전체 조회 API
     * [GET] /posters/town
     * @return BaseResponse<GetProfileRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetTownRes>> getTownPost() {
        try{
            // Get All Town Posts
            List<GetTownRes> getTownRes = townProvider.getTowns();
            return new BaseResponse<>(getTownRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 동네 생활 게시글 개별 조회 API
     * [GET] /posters/town/:townpostId
     * @return BaseResponse<GetProfileRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{townpostId}")
    public BaseResponse<GetTownRes> getTownPost(@PathVariable("townpostId") int id) {
        // Get ProfileUsers
        try{
            GetTownRes gettownRes = townProvider.getTown(id);
            return new BaseResponse<>(gettownRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }
    /**
     * 동네 생활 게시글 수정 API
     * [PATCH] /posters/town/:townpostId
     */
    @ResponseBody
    @PatchMapping("/{townpostId}")
    public BaseResponse<String> updateTown(@PathVariable("townpostId") int id, @RequestBody Town town) {
        try {
            PatchTownReq patchTownReq = new PatchTownReq(
                    town.getContent(),
                    town.getModifiedAt(),
                    id);
            townService.updateTown(patchTownReq);

            String result = "동네 생활 정보가 수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @DeleteMapping("/{townpostId}")
    public BaseResponse<String> deleteTown(@PathVariable("townpostId") int id, @RequestBody DeleteTownReq deleteTown) {
        try {
            DeleteTownReq deleteTownReq = new DeleteTownReq(deleteTown.getId());
            townService.deleteTownPost(deleteTownReq);

            String result = "동네 포스트가 삭제되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
