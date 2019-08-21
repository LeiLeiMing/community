package com.lm.community.Domain;

import lombok.Data;

/**
 * github用户
 */
@Data
public class GithubUser {

    private Long id;
    private String name;
    private String bio;
    private String avatar_url;

}
