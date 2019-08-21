package com.lm.community.CommunityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LaunchController {

    @GetMapping("/launch")
    public String launch(){
        return "/launch";
    }
}
