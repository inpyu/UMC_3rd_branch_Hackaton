package com.example.demo.src.profile;


import com.example.demo.config.BaseException;
import com.example.demo.src.profile.model.GetProfileRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class ProfileProvider {

    private final ProfileDao profileDao;



    /**
     * 프로필 조회를 위한 User 정보 획득
     * */
    public GetProfileRes getProfileUser(int id) throws BaseException {
        try{
            GetProfileRes getProfileRes = profileDao.getProfileUser(id);
            return getProfileRes;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
