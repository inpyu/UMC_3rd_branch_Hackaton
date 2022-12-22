package com.example.demo.src.badge;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.badge.model.GetBadgeRes;
import com.example.demo.src.profile.ProfileProvider;
import com.example.demo.src.profile.model.GetProfileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/badge")
public class BadgeController {

    @Autowired
    private final BadgeProvider badgeProvider;


    /**
     * 유저 뱃지 조회 API
     * [GET] /badge/:userid
     * @return BaseResponse<GetProfileRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userId}")
    public BaseResponse<GetBadgeRes> getBadge(@PathVariable("userId") int id) {
        // Get Badge
        try{
            GetBadgeRes getBadgeRes = badgeProvider.getBadge(id);
            return new BaseResponse<>(getBadgeRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
