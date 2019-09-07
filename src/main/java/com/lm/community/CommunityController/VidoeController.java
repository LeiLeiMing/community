package com.lm.community.CommunityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VidoeController {
    @GetMapping("/video/index")
    public String index(){
        return "video";
    }

    @GetMapping("/video/watch")
    public String watch(){
        return "watchvideo";
    }
}
