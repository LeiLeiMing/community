package com.lm.community.Service.impl;

import com.lm.community.Dao.CommentDao;
import com.lm.community.Dao.LaunchDao;
import com.lm.community.Dao.RecommentDao;
import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Recomment;
import com.lm.community.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private LaunchDao launchDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private RecommentDao recommentDao;

    @Override
    public void saveComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> findAllCommentById(Integer id) {
        return commentDao.findAllCommentById(id);
    }




}
