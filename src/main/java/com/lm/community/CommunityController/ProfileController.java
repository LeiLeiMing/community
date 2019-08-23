package com.lm.community.CommunityController;

import com.lm.community.Domain.Question;
import com.lm.community.Service.LoginService;
import com.lm.community.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {


    @Autowired
    private LoginService loginService;
    @Autowired
    private PageService pageService;


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model, HttpServletRequest request,
             @RequestParam(name = "page",defaultValue = "1")Integer page, @RequestParam(name = "size",defaultValue = "1")Integer size){
        //登录判断
        if(loginService.checkCookie(request)==null){
            model.addAttribute("error","未登录");
            return "redirect:/";
        }

        if("questions".equals(action)){
            //调用Service查询当前用户id下的提问文章
            List<Question> question = pageService.findQuestionByUserId(page, size, model, request);
            model.addAttribute("allquestion",question);
            model.addAttribute("questions","questions");
            model.addAttribute("prifiletitle","我的提问");
            model.addAttribute("key","questions");
        }else if("remessage".equals(action)){
            model.addAttribute("remessages","remessages");
            model.addAttribute("prifiletitle","收到的回复");
            model.addAttribute("key","remessage");
        }
        return "profile";
    }
}
