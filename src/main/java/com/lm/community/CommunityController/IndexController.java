package com.lm.community.CommunityController;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Question;
import com.lm.community.Domain.Recomment;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.IndexService;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LaunchService launchService;
    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "10")Integer size){
        //分页查询所有的提问
        List<Question> allquestion = launchService.findAllQuestionByLimi(page,size, model);
        //获取当前登录的用户
        SaveSession user = (SaveSession) request.getSession().getAttribute("user");
        //查询当前id下的所有一级未读评论（数据库设计存在缺陷，导致只能根据name来查询）
        List<Comment> notReadComment = indexService.AllNotReadComment(user.getName());
        //查询所有二级未读评论
        List<Recomment> notRecommentCount = indexService.AllNotRecommentCount(user.getName());
        //查询所有未（总）读评论数量
        Integer allNotReadCount = indexService.findAllNotReadCount(user.getName());
        for(Comment comment : notReadComment){
            System.out.println("一级评论："+comment.getComment());
        }
        for(Recomment recomment1 : notRecommentCount){
            System.out.println("二级评论："+recomment1.getRecomment());
        }
        model.addAttribute("allquestion",allquestion);
        return "index";
    }
}
