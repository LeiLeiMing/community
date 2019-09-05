package com.lm.community.Domain;

import lombok.Data;

/**
 * 评论点赞
 */
@Data
public class Likequestion {
    private Integer id;
    private Integer questionid;
    private Integer likeuserid;
    private Integer status;
}
