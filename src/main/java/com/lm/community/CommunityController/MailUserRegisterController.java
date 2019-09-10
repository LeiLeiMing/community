package com.lm.community.CommunityController;

import com.lm.community.Domain.MailUser;
import com.lm.community.Service.MailUserService;
import com.lm.community.Utils.CommentCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱用户
 * 用户应该具有和github属性一样的特征
 * 以邮箱地址为唯一标志
 */
@Controller
public class MailUserRegisterController {

    @Autowired
    private MailUserService mailUserService;

    /**
     * 注册用户
     */
    @ResponseBody
    @PostMapping("/mailuser/register")
    public Object register(MailUser mailUser,String code, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //密码正则
        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

        //判断是否有该用户了
        MailUser user = mailUserService.checkUser(mailUser.getName());
        if(user!=null){
            map.put("message","error");
            return map; //已有该用户，注册失败
        }

        //密码正则校验
        if(!mailUser.getPassword().matches(reg)){
            map.put("message","passworderror");
            return map;
        }

        //判断验证码
        if("".equals(code)&&code==null&&CommentCheck.check(code)==false){
            map.put("message","codeerror");
            return map;
        }

        //发送的验证码
        String syscode = (String) request.getSession().getAttribute("code");
        if(syscode==null){
            map.put("message","syscodenull");
            return map;
        }

        //核实验证码
        if(!code.equals(syscode)){
            map.put("message","codeerror");
            return map;
        }
        //手动设置默认头像
        mailUser.setAvatar_url("https://leiming-zhiqiu.oss-cn-beijing.aliyuncs.com/boy.png");
        mailUserService.registerMailUser(mailUser);
        map.put("message","success");
        return map; //注册成功
    }

    @ResponseBody
    @PostMapping("/mailuser/check")
    public Object checkUser(String name){
        Map<String,Object> map = new HashMap<>();
        //开始核对该用户是否存在
        MailUser user = mailUserService.checkUser(name);
        if(user!=null){
            map.put("message","userisexit");
            return map;
        }
        map.put("message","ok");
        return map;
    }

    @ResponseBody
    @PostMapping("/mailuser/checkcode")
    public Object checkcode(String code){
        //开始核对

        Map<String,Object> map = new HashMap<>();
        map.put("message","success");
        return map;
    }
}
