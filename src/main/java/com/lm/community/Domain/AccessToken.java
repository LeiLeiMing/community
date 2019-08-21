package com.lm.community.Domain;

import lombok.Data;

/**
 * accesstoken码
 */
@Data
public class AccessToken {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
