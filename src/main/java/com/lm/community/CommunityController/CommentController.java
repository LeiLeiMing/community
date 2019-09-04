package com.lm.community.CommunityController;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Recomment;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.CommentService;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.RecommentService;
import com.lm.community.Utils.CommentCheck;
import com.lm.community.Utils.getAppendComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private LaunchService launchService;
    @Autowired
    private RecommentService recommentService;

    /**
     * 评论功能
     * @param comment
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody Comment comment, HttpServletRequest request){

        Map<Object, Object> map = new HashMap<>();

        //判断是否全部为空格
        if(CommentCheck.check(comment.getComment())==false){
            map.put("message","commentisnull");
            return map;
        }

        if(comment.getComment()==""){
            map.put("message","commentisnull");
            return map;
        }
        if(request.getSession().getAttribute("user")==null){
            map.put("message","notlogin");
            return map;
        }
        //把评论存进数据库
        comment.setCommenttime(new Date());
        commentService.saveComment(comment);
        //更新评论数量
        launchService.updateCommentCount(comment.getQuestionid());
        //查询该ID下的文章的所有评论
        List<Comment> comments = commentService.findAllCommentById(comment.getQuestionid());
        //把comment放进Session
        request.getSession().setAttribute("comments",comments);
        map.put("message","success");
        return map;
    }

    /**
     * 回复功能
     */
    @ResponseBody
    @PostMapping("/recomment")
    public Object recomment(@RequestBody Recomment recomment, HttpServletRequest request){
        Map<Object, Object> map = new HashMap<>();
        //判断是否全部为空格
        if(CommentCheck.check(recomment.getRecomment())==false){
            map.put("message","commentisnull");
            return map;
        }
        if(recomment.getRecomment()==""){
            map.put("message","commentisnull");
            return map;
        }
        if(request.getSession().getAttribute("user")==null){
            map.put("message","notlogin");
            return map;
        }
        recomment.setRecommenttime(new Date());
        //通过检查后，将数据存进数据库
        recommentService.saveRecomment(recomment);
        //查询该评论id下的所有评论展示到前端
        List<Comment> comments = commentService.findAllCommentById(recomment.getQuestionid());
        //把comment放进Session
        request.getSession().setAttribute("comments",comments);
        map.put("message","success");
        return map;
    }

    /**
     * 把一级评论标为已读
     * @param id
     * @return
     */
    @GetMapping("/comment/{id}")
    public String markReadComment(@PathVariable(name="id")Integer id){
        commentService.markReadComment(id);
        return "redirect:/profile/remessage";
    }

    /**
     * 把二级评论标为已读
     * @param id
     * @return
     */
    @GetMapping("/recomment/{id}")
    public String markReadReComment(@PathVariable(name="id")Integer id){
        recommentService.markReadRecomment(id);
        return "redirect:/profile/remessage";
    }
}
