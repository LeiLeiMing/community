package com.lm.community.CommunityController;

import com.lm.community.Domain.AccessToken;
import com.lm.community.Domain.GithubUser;
import com.lm.community.Provider.GithubUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    /**
     * 接收github登录成功的code
     * @param code
     * @param state
     * @return
     */
    @Autowired
    private GithubUtils githubUtils;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_url;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state){
        AccessToken token = new AccessToken();
        token.setClient_id(client_id);
        token.setClient_secret(client_secret);
        token.setCode(code);
        token.setRedirect_uri(redirect_url);
        token.setState(state);
        String accessToken = githubUtils.getAccessToken(token);
        GithubUser user = githubUtils.getUser(accessToken);
        String name = user.getName();
        System.out.println(name);
        return "index";
    }
}
