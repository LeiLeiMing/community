package com.lm.community.CommunityController;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Question;
import com.lm.community.Service.CommentService;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.PageService;
import com.lm.community.Service.RecommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * question
 */
@Controller
public class QuestionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PageService pageService;

    @Autowired
    private LaunchService launchService;


    /**
     * 指定id的question
     * @param id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model, HttpServletRequest request){
        //新增阅读数
        pageService.updateViewCount(id);
        //根据id查询文章
        Question question = pageService.findQuestionById(id);
        //评论数
        Integer count = launchService.findAllCommentById(id);
        question.setCommentcount(count);
        //查询该ID下的文章的所有评论
        List<Comment> comments = commentService.findAllCommentById(id);
        //查询相似问题
        String[] tags = question.getTag().split(" ");
        List<Question> simileQuestion = pageService.findSimleQuestion(tags, question.getId());
        //把相似问题放进Session
        request.getSession().setAttribute("similequestion",simileQuestion);
        //把comment放进Session
        request.getSession().setAttribute("comments",comments);
        model.addAttribute("question",question);
        return "questions";
    }

    /**
     * 编辑
     * @param question
     * @param model
     * @return
     */
    @PostMapping("/question/edit")
    public String edit(Question question,Model model){
        //更新信息
        pageService.editQuestionById(question);
        //重新查询
        Question question1 = pageService.findQuestionById(question.getId());
        model.addAttribute("question",question1);
        return "questions";
    }

    @PostMapping("/question/search")
    public String search( String searchquestion,Model model){
        //模糊查询  以标题  标签 文章作者为关键字进行模糊搜索
        List<Question> questions = pageService.searchQuestion(searchquestion);
        model.addAttribute("search",questions);
        model.addAttribute("searchkey",searchquestion);
        return "search";
    }
}
