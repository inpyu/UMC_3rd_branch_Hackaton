package com.example.demo.src.useditemposter;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.useditemposter.model.PatchUsedItemPosterReq;
import com.example.demo.src.useditemposter.model.PostUsedItemPosterReq;
import com.example.demo.src.useditemposter.model.PostUsedItemPosterRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsedItemPosterService {

    private final UsedItemPosterDao usedItemPosterDao;

    public PostUsedItemPosterRes createUsedItemPoster(PostUsedItemPosterReq postUsedItemPosterReq) throws BaseException {
        //validation

        try{
            int postId = usedItemPosterDao.createUsedItemPoster(postUsedItemPosterReq);
            return new PostUsedItemPosterRes(postId);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }

    public void modifyUsedItemPoster(int postId,PatchUsedItemPosterReq patchUsedItemPosterReq) throws BaseException {

        int result = usedItemPosterDao.modifyUsedItemPoster(postId, patchUsedItemPosterReq);
        if(result == 0) {
                throw new BaseException(BaseResponseStatus.MODIFYING_ERROR);
        }
    }

}
