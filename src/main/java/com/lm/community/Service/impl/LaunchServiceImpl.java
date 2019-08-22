package com.lm.community.Service.impl;

import com.lm.community.Dao.LaunchDao;
import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("launchService")
public class LaunchServiceImpl implements LaunchService {

    @Autowired
    private LaunchDao launchDao;
    @Autowired
    private LoginService loginService;

    @Override
    public void saveQuestion(Question question, HttpServletRequest request) {
        SaveSession user = loginService.checkCookie(request);
        question.setAuthor(user.getId());
        question.setCreatetime(new Date());
        System.out.println(question);
        launchDao.saveQuestion(question);
    }

    /**
     * 查询所有的提问
     * @return
     */
    @Override
    public List<Question> findAllQuestion() {

        return launchDao.findAllQuestion();
    }
}
