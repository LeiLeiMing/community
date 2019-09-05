package com.lm.community.Service.impl;

import com.lm.community.Dao.LikeQuestionDao;
import com.lm.community.Domain.Likequestion;
import com.lm.community.Service.LikeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("likequestionservice")
@Transactional
public class LikeQuestionServiceImpl implements LikeQuestionService {
    @Autowired
    private LikeQuestionDao likeQuestionDao;

    @Override
    public void saveLike(Likequestion likequestion) {
        likeQuestionDao.saveLike(likequestion);
    }

    @Override
    public Likequestion findLikeByUserAndQid(Integer questionid, Integer likeuserid) {
        return likeQuestionDao.findLikeByUserAndQid(questionid,likeuserid);
    }
}
