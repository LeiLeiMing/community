package com.lm.community.Service.impl;

import com.lm.community.Dao.LoginDao;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;


    /**
     * 登录前会调用这个方法进行Cookie判断
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean checkCookie( HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if("token".equals(cookie.getName())){
                    String value = cookie.getValue();
                    //核对数据库是否有这个value
                    SaveSession user = loginDao.findSessionByToken(value);
                    System.out.println("查到的cookie为："+user.getUsername());
                    if(user!=null){
                        //有，把查询到的用户名放进去
                        request.getSession().setAttribute("user",user);
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
