package com.example.demo.src.profile;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.profile.model.GetProfileRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class ProfileController {

    @Autowired
    private final ProfileProvider profileProvider;

    public ProfileController(ProfileProvider profileProvider){
        this.profileProvider = profileProvider;
    }


    /**
     * 유저페이지 조회 API
     * [GET] /users/:userid
     * @return BaseResponse<GetProfileRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userId}")
    public BaseResponse<GetProfileRes> getProfileUser(@PathVariable("userId") int id) {
        // Get ProfileUsers
        try{
            GetProfileRes getProfileRes = profileProvider.getProfileUser(id);
            return new BaseResponse<>(getProfileRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
