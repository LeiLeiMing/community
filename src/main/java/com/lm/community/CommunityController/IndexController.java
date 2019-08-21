package com.lm.community.CommunityController;

import com.lm.community.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,HttpServletResponse response){
        //loginService.checkCookie(request,response);
        return "index";
    }
}
