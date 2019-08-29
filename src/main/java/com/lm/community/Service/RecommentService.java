package com.lm.community.Service;

import com.lm.community.Domain.Recomment;

public interface RecommentService {

    Integer findAllRecommentCount(Integer id);

    void saveRecomment(Recomment recomment);

    void markReadRecomment(Integer id);

}
