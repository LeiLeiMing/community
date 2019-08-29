package com.lm.community.Service.impl;

import com.lm.community.Dao.RecommentDao;
import com.lm.community.Domain.Recomment;
import com.lm.community.Service.RecommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("recommentService")
@Transactional
public class RecommentServiceImpl implements RecommentService {

    @Autowired
    private RecommentDao recommentDao;

    @Override
    public Integer findAllRecommentCount(Integer id) {
        return recommentDao.findAllRecommentCount(id);
    }

    /**
     * 保存回复
     * @param recomment
     */
    @Override
    public void saveRecomment(Recomment recomment) {
        recommentDao.saveRecomment(recomment);
    }

    /**
     * 二级评论标为已读
     * @param id
     */
    @Override
    public void markReadRecomment(Integer id) {
        recommentDao.markReadRecomment(id);
    }
}
