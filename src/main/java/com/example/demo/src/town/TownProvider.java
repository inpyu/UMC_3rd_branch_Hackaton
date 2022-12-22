package com.example.demo.src.town;

import com.example.demo.config.BaseException;
import com.example.demo.src.town.model.GetTownRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class TownProvider {

    @Autowired
    private final TownDao townDao;

    /**
     * 모든 마을 소식 리스트 Provider
     * */
    public List<GetTownRes> getTowns() throws BaseException {
        try{
            List<GetTownRes> getTownRes = townDao.getTowns();
            return getTownRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /**
     * 단일 마을 소식 리스트 Provider
     * */
    public GetTownRes getTown(int townIdx) throws BaseException {
        try {
            GetTownRes getTownRes = townDao.getTown(townIdx);
            return getTownRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
