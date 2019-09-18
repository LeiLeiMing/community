package com.lm.community.Service.impl;

import com.lm.community.Dao.TopicalDao;
import com.lm.community.Service.TopicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("topicalService")
public class TopicalServiceImpl implements TopicalService {

    @Autowired
    private TopicalDao topicalDao;

    @Override
    public void addTopical(Integer questionid,Integer topical) {
        topicalDao.addTopical(questionid,topical);
    }
}
