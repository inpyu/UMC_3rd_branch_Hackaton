package com.example.demo.src.town;

import com.example.demo.config.BaseException;
import com.example.demo.src.town.model.DeleteTownReq;
import com.example.demo.src.town.model.GetTownReq;
import com.example.demo.src.town.model.PatchTownReq;
import com.example.demo.src.town.model.PostTownRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class TownService {

    @Autowired
    private final TownDao townDao;

    /**
     * 동네 생활 포스트 생성
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
    /**
     * 동네 생활 포스트 수정
     * */
    public void updateTown(PatchTownReq patchTownReq) throws BaseException {
        try {
            int result = townDao.updateTown(patchTownReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 동네 생활 포스트 삭제
     * */
    public void deleteTownPost(DeleteTownReq deleteTownReq) throws BaseException {
        try {
            int result = townDao.deleteTown(deleteTownReq);
            if (result == 0) {
                throw new BaseException(DELETE_FAIL_TOWN);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
