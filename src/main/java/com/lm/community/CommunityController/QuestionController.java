package com.lm.community.CommunityController;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Likequestion;
import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.*;
import com.lm.community.Utils.LaunchCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @Autowired
    private LikeQuestionService likeQuestionService;


    /**
     * 指定id的question
     * @param id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model, HttpServletRequest request){
        //获取当前用户
        SaveSession user = (SaveSession) request.getSession().getAttribute("user");
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
        //问题点赞数
        Integer likecount = pageService.likecount(question.getId());
        //查询当前用户下对该篇问题点赞的情况，如果有，则不允许继续点赞
        Likequestion likeByUserAndQid = likeQuestionService.findLikeByUserAndQid(question.getId(), user.getId());
        //标记红色已点赞图标
        if(likeByUserAndQid!=null){
            model.addAttribute("likekey","red");
        }
        model.addAttribute("like",likecount);
        model.addAttribute("question",question);
        return "questions";
    }

    /**
     * 转至编辑页面
     * @param
     * @return
     */
    @GetMapping("/question/toedit/{id}")
    public String toedit(@PathVariable(name = "id")Integer id,Model model){
        //重新查询
        Question oldquestion = pageService.findQuestionById(id);
        model.addAttribute("old",oldquestion);
        return "edit";
    }

    /**
     * 保存编辑
     * @param question
     * @param model
     * @return
     */
    @PostMapping("/question/edit")
    public String edit(Question question,Model model,HttpServletRequest request){
        if(LaunchCheck.check(question.getTitle(), question.getDesction(), question.getTag())==false){
            request.getSession().setAttribute("error","填写内容不能为空，请重新发布");
            return "redirect:/question/toedit/"+question.getId();
        }
        //更新信息
        pageService.editQuestionById(question);
        //重新查询
        Question question1 = pageService.findQuestionById(question.getId());
        model.addAttribute("question",question1);
        return "redirect:/question/"+question.getId();
    }

    @PostMapping("/question/search")
    public String search( String searchquestion,Model model){
        //模糊查询  以标题  标签 文章作者为关键字进行模糊搜索
        List<Question> questions = pageService.searchQuestion(searchquestion);
        model.addAttribute("search",questions);
        model.addAttribute("searchkey",searchquestion);
        return "search";
    }


    /**
     * 点赞功能
     * 用户一点赞，先判断该用户是否登录，诺没有登录先提示登录
     * 正常用户点赞后，先会把当前用户（点赞用户id存进linkauthor）
     * 异步返回当前点赞数（likecount=likecount+1）,异步返回并把点赞图标变红
     * 下次再进入该文章将会查询该文章下当前用户是否点赞
     */
    @ResponseBody
    @PostMapping("/question/like")
    public Object like(@RequestBody Question question,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //获取当前点赞的用户
        SaveSession user = (SaveSession) request.getSession().getAttribute("user");
        if(user==null){
            map.put("message","userisnull");
            return map;
        }
        //查询当前用户下对该篇问题点赞的情况，如果有，则不允许继续点赞
        Likequestion likeByUserAndQid = likeQuestionService.findLikeByUserAndQid(question.getId(), user.getId());
        if(likeByUserAndQid!=null){
            map.put("message","hadlike");
            return map;
        }
        Likequestion likequestion = new Likequestion();
        likequestion.setLikeuserid(user.getId());
        likequestion.setQuestionid(question.getId());
        likequestion.setStatus(1);
        //把点赞的信息存进去
        likeQuestionService.saveLike(likequestion);
        //更新问题点赞数
        pageService.updateLikecount(question.getId());
        //点赞总数
        Integer likecount = pageService.likecount(question.getId());
        //将点赞成功的信息返回
        map.put("message","success");
        map.put("likecount",likecount);
        return map;
    }
}
