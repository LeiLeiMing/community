package com.lm.community.CommunityController;

import com.lm.community.Domain.MailUser;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.MailUserService;
import com.lm.community.Service.SessionService;
import com.lm.community.Utils.CommentCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * login
 */
@Controller
public class MailUserLoginController {

    @Autowired
    private MailUserService mailUserService;

    @Autowired
    private SessionService sessionService;

    /**
     * 邮箱用户登录
     * @param mailUser
     * @param code
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @PostMapping("/mailuser/login")
    public Object login(MailUser mailUser, String code, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        System.out.println(mailUser+":"+code);
        //判断验证码是否为空
        if("".equals(code)&&code==null&& CommentCheck.check(code)==false){
            map.put("message","codenull");
            return map;
        }
        //核实验证码
        String syscode = (String) request.getSession().getAttribute("code");
        if(syscode==null){
            map.put("message","syscodenull");
            return map;
        }
        if(!code.equals(syscode)){
            map.put("message","codeerror");
            return map;
        }
        //核实是否存在该用户
        MailUser user = mailUserService.checkUser(mailUser.getName());
        if(user==null){
            map.put("message","userisnull");
        }
        //开始正式验证账号密码
        //根据邮箱查找账号和密码
        MailUser sysuser = mailUserService.findLoginUser(mailUser.getPassword(), mailUser.getName());
        if(sysuser==null){
            map.put("message","loginerror");
            return map;
        }
        SaveSession session = new SaveSession();
        session.setTime(new Date());
        String s = UUID.randomUUID().toString();
        session.setToken(s);
        session.setAvatar_url(sysuser.getAvatar_url());
        session.setName(sysuser.getName());
        sessionService.saveSession(session);
        //获取保存到SaveSession中的对象
        SaveSession sesionuser = sessionService.findSavesession(s);
        //把用户存进session
        request.getSession().setAttribute("user",sesionuser);
        //保存到Cookie中
        Cookie cookie = new Cookie("token",s);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        //设置验证码失效
        request.getSession().setAttribute("code",null);
        map.put("message","success");
        return map; //登录成功
    }
}
