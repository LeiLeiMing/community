package com.lm.community.Service;

import com.lm.community.Domain.Likequestion;

public interface LikeQuestionService {

    public void saveLike(Likequestion likequestion);

    Likequestion findLikeByUserAndQid(Integer questionid,Integer likeuserid);
}
