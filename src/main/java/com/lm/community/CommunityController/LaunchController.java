package com.lm.community.CommunityController;

import com.lm.community.Domain.Question;
import com.lm.community.Service.LaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LaunchController {

    @Autowired
    private LaunchService launchService;

    @GetMapping("/launch")
    public String launch(HttpServletRequest request){
        //判断用户是否登录
        Object user = request.getSession().getAttribute("user");
        if(user!=null){
            return "/launch";
        }
        return "redirect:/";
    }

    /**
     * 发起问题
     * @return
     */
    @PostMapping("/dolaunch")
    public String dolaunch(Question question,HttpServletRequest request){
        if(question!=null){
            //设置一些数据的初始值
            question.setLikecount(0);
            question.setCommentcount(0);
            question.setViewcount(0);
            //存进数据库
            launchService.saveQuestion(question,request);
            return "redirect:/";
        }
        request.getSession().setAttribute("error","提交数据不能为空");
        return "/launch";
    }
}