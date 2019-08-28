package com.lm.community.Service.impl;

import com.lm.community.Dao.CommentDao;
import com.lm.community.Dao.RecommentDao;
import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Recomment;
import com.lm.community.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("indexService")
@Transactional
public class IndexServiceImpl implements IndexService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private RecommentDao recommentDao;

    /**
     * 未读评论/回复
     * @return
     */
    @Override
    public List<Comment> AllNotReadComment(String name) {
        return commentDao.findAllNotReadComment(name);
    }

    @Override
    public List<Recomment> AllNotRecommentCount(String name) {
        return recommentDao.findAllNotRecommentCount(name);
    }

    @Override
    public Integer findAllNotReadCount(String name) {
        return commentDao.finsAllNotReadCommentCount(name)+recommentDao.findAllNotReadRecomment(name);
    }
}
