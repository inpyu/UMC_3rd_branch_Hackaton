package com.example.demo.src.badge;

import com.example.demo.config.BaseException;
import com.example.demo.src.badge.model.GetBadgeRes;
import com.example.demo.src.profile.ProfileDao;
import com.example.demo.src.profile.model.GetProfileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class BadgeProvider {

    private final BadgeDao badgeDao;

    /**
     * 뱃지 조회를 위한 User 정보 획득
     * */
    public GetBadgeRes getBadge(int id) throws BaseException {
        try{
            GetBadgeRes getBadgeRes = badgeDao.getBadge(id);
            return getBadgeRes;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
