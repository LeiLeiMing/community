package com.lm.community.CommunityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComController {

    @GetMapping("/")
    public String hello(){
        return "index";
    }
}
