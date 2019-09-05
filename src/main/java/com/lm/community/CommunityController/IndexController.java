package com.lm.community.CommunityController;

import com.lm.community.Domain.Comment;
import com.lm.community.Domain.Question;
import com.lm.community.Domain.Recomment;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.IndexService;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.LoginService;
import com.lm.community.Service.PageService;
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
    @Autowired
    private PageService pageService;


    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "20")Integer size){
        //分页查询所有的提问
        List<Question> allquestion = launchService.findAllQuestionByLimi(page,size, model);
        //获取当前登录的用户
        SaveSession user = (SaveSession) request.getSession().getAttribute("user");
        if(user!=null){
            //查询所有未（总）读评论数量
            Integer allNotReadCount = indexService.findAllNotReadCount(user.getName());
            request.getSession().setAttribute("allnotreadcount",allNotReadCount);
        }
        //查询热门文章
        List<Question> hostQuestions = pageService.findHostQuestions();
        request.getSession().setAttribute("host",hostQuestions);
        //查询最新文章
        List<Question> newQuestion = pageService.findNewQuestion();
        request.getSession().setAttribute("new",newQuestion);
        model.addAttribute("allquestion",allquestion);
        return "index";
    }
}
