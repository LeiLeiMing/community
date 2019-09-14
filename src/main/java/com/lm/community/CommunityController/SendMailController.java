package com.lm.community.CommunityController;

import com.lm.community.Utils.MailUtils;
import com.lm.community.Utils.RandomCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * send mail code
 */
@Controller
public class SendMailController {

    @ResponseBody
    @PostMapping("/mailuser/sendcode")
    public void sendMail(String mail, HttpServletRequest request) throws Exception {
        String code = RandomCode.getCode().toString();
        //发送邮箱
        MailUtils.sendMsg(mail,"知秋论坛验证码", code);
        //把验证码放在Session
        request.getSession().setAttribute("code",code);
        //设置过期时间50s
        System.out.println("mail send ok！");
    }

}
