package com.lm.community.Domain;

import lombok.Data;

import java.util.Date;
@Data
public class Question {
    private Integer id;
    private String desction;
    private Date createtime;
    private Date modiftime;
    private Integer author;
    private Integer commentcount;
    private Integer viewcount;
    private Integer likecount;
    private String tag;
    private String title;
    //文章和当前Cookie中的用户是多对一
    private SaveSession saveSession;

}
