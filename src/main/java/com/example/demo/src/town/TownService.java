package com.example.demo.src.town;

import com.example.demo.config.BaseException;
import com.example.demo.src.town.model.GetTownReq;
import com.example.demo.src.town.model.PostTownRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class TownService {

    @Autowired
    private final TownDao townDao;

    /**
     * 마을 포스트 생성
     * */
    public PostTownRes createTownPost(GetTownReq getTownReq) throws BaseException {
        try {
            int postIdx = townDao.createTownPost(getTownReq);
            return new PostTownRes(postIdx);

        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
