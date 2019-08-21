package com.lm.community.Service;


import com.lm.community.Domain.SaveSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    SaveSession checkCookie(HttpServletRequest request);
}
