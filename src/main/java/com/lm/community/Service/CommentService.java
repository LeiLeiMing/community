package com.lm.community.Service;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Recomment;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> findAllCommentById(Integer id);



}
