package com.lm.community.Domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Integer id;
    private Integer questionid;
    private String comment;
    private Integer commentor;
    private Date commenttime;
    private Integer commentcount;
    private Integer read;//评论是否已读  1为未读，0为已读

    private SaveSession user;


    //该条评论下有哪些评论
    //一对多
    private List<Recomment> recomment;

    //该评论有多少回复
    private Integer recommentcount;
}
