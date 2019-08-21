package com.lm.community.CommunityController;

import com.lm.community.Domain.Question;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LaunchService launchService;

    @GetMapping("/")
    public String hello(HttpServletRequest request, Model model){
        loginService.checkCookie(request);
        //查询所有的提问
        List<Question> allquestion = launchService.findAllQuestion();
        model.addAttribute("allquestion",allquestion);
        return "index";
    }
}
