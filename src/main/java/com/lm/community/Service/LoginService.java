package com.lm.community.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    boolean checkCookie(HttpServletRequest request, HttpServletResponse response);
}
