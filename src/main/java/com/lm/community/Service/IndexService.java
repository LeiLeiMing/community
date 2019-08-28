package com.lm.community.Service;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Recomment;

import java.util.List;

public interface IndexService {

    //一级未读评论
    List<Comment> AllNotReadComment(String name);
    //二级未读评论
    List<Recomment> AllNotRecommentCount(String name);

    Integer findAllNotReadCount(String name);
}
