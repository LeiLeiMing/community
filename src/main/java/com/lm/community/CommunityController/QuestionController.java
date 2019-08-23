package com.lm.community.CommunityController;

import com.lm.community.Domain.Question;
import com.lm.community.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private PageService pageService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        //根据id查询文章
        Question question = pageService.findQuestionById(id);
        model.addAttribute("question",question);
        System.out.println(question);
        return "questions";
    }
}
