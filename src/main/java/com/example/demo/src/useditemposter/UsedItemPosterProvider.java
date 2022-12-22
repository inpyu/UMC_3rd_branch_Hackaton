package com.example.demo.src.useditemposter;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.useditemposter.model.GetUsedItemPosterRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsedItemPosterProvider {

    private final int SIZE = 10;
    private final UsedItemPosterDao usedItemPosterDao;

    public GetUsedItemPosterRes getUsedItemPosterById(int id) throws BaseException {
        try {
            return usedItemPosterDao.getUsedItemPoster(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BaseException(REQUEST_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }

    }

    /*
    public void getUsedItemPostersByUserId(int userId,int offset) {

    }*/

    public List<GetUsedItemPosterRes> getUsedItemPostersByCategoryId(int categoryId, int page) throws BaseException {

        int offset = page * SIZE;
        try {
            return usedItemPosterDao.findByCategoryId(categoryId, SIZE, offset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public List<GetUsedItemPosterRes> getUsedItemPosters(int page) throws BaseException {
        int offset = page * SIZE;
        try{
            return usedItemPosterDao.findAll(SIZE, offset);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
