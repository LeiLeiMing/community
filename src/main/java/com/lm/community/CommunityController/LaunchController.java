package com.lm.community.CommunityController;

import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.IndexService;
import com.lm.community.Service.LaunchService;
import com.lm.community.Utils.LaunchCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LaunchController {

    @Autowired
    private LaunchService launchService;
    @Autowired
    private IndexService indexService;

    @GetMapping("/launch")
    public String launch(HttpServletRequest request){
        //判断用户是否登录
        SaveSession user = (SaveSession) request.getSession().getAttribute("user");
        if(user!=null){
            //查询所有未（总）读评论数量
            Integer allNotReadCount = indexService.findAllNotReadCount(user.getName());
            request.getSession().setAttribute("allnotreadcount",allNotReadCount);
            return "launch";
        }
        return "redirect:/";
    }

    /**
     * 发起问题
     * @return
     */
    @PostMapping("/dolaunch")
    public String dolaunch(Question question, HttpServletRequest request, Model model){
        //核实是否有空格
        String title = question.getTitle();
        String desction = question.getDesction();
        String tag = question.getTag();
        if(question!=null){
            //核实空格
            if(LaunchCheck.check(title, desction, tag)==false){
               request.getSession().setAttribute("error","不能有全部空格项，请重新发布");
                return "redirect:/launch";
            }
            //设置一些数据的初始值
            question.setLikecount(0);
            question.setCommentcount(0);
            question.setViewcount(0);
            //存进数据库
            launchService.saveQuestion(question,request);
            return "redirect:/";
        }
        request.getSession().setAttribute("error","提交数据不能为空");
        return "redirect:/launch";
    }
}
