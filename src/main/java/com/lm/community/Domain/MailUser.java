package com.lm.community.Domain;

import lombok.Data;

@Data
public class MailUser {

    private Integer id;
    private String name;
    private String password;
    private String avatar_url;
    private String markname;
    private String sex;
}
