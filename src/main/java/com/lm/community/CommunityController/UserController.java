package com.lm.community.CommunityController;

import com.lm.community.Domain.AccessToken;
import com.lm.community.Domain.GithubUser;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Provider.GithubUtils;
import com.lm.community.Service.GithubUserService;
import com.lm.community.Service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
@Slf4j  //日志注解
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
    @Autowired
    private SessionService sessionService;
    @Autowired
    private GithubUserService githubUserService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessToken token = new AccessToken();
        token.setClient_id(client_id);
        token.setClient_secret(client_secret);
        token.setCode(code);
        token.setRedirect_uri(redirect_url);
        token.setState(state);
        System.out.println("token:"+token);
        String accessToken = githubUtils.getAccessToken(token);
        GithubUser user = githubUtils.getUser(accessToken);
        if(user!=null){
            //用户信息存进数据库
            githubUserService.saveGithubUser(user);
            //把用户存进session
            request.getSession().setAttribute("user",user);
            SaveSession session = new SaveSession();
            session.setTime(new Date());
            String s = UUID.randomUUID().toString();
            session.setToken(s);
            session.setAvatar_url(user.getAvatar_url());
            session.setName(user.getName());
            sessionService.saveSession(session);
            //保存到Cookie中
            Cookie cookie = new Cookie("token",s);
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
            //重定向至首页
            return "redirect:/";
        }else{
            //打印登录失败日志
            log.error("login error,user is null");
            return "redirect:/";
        }
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
