package com.lm.community.CommunityController;

import com.lm.community.Domain.Question;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LaunchService launchService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "10")Integer size){
        //分页查询所有的提问
        List<Question> allquestion = launchService.findAllQuestionByLimi(page,size, model);
        model.addAttribute("allquestion",allquestion);
        return "index";
    }
}
