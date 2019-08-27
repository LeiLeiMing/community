package com.lm.community.CommunityController;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Question;
import com.lm.community.Service.CommentService;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.PageService;
import com.lm.community.Service.RecommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PageService pageService;

    @Autowired
    private LaunchService launchService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model, HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            return "redirect:/";
        }
        //新增阅读数
        pageService.updateViewCount(id);
        //根据id查询文章
        Question question = pageService.findQuestionById(id);
        //评论数
        Integer count = launchService.findAllCommentById(id);
        question.setCommentcount(count);
        //查询该ID下的文章的所有评论
        List<Comment> comments = commentService.findAllCommentById(id);
        //把comment放进Session
        request.getSession().setAttribute("comments",comments);
        model.addAttribute("question",question);
        return "questions";
    }
}
