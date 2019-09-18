package com.lm.community.CommunityController;

import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.IndexService;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.LoginService;
import com.lm.community.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TopicalController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LaunchService launchService;
    @Autowired
    private IndexService indexService;
    @Autowired
    private PageService pageService;

    @GetMapping("/topical/{id}")
    public String javatopical(@PathVariable(name = "id")Integer id, HttpServletRequest request, Model model,
                              @RequestParam(name = "page",defaultValue = "1")Integer page,
                              @RequestParam(name = "size",defaultValue = "20")Integer size){
        //分页查询该话题下所有提问
        //java话题
        if(id==1){
            List<Question> allquestion = launchService.findAllTopicalQuestionByLimi(page,size,model,id);
            model.addAttribute("allquestion",allquestion);
            return "topical_java";
        }
        return null;
    }
}
