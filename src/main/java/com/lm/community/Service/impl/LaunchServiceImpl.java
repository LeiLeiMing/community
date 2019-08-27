package com.lm.community.Service.impl;

import com.lm.community.Dao.CommentDao;
import com.lm.community.Dao.LaunchDao;
import com.lm.community.Domain.Page;
import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.LaunchService;
import com.lm.community.Service.LoginService;
import com.lm.community.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service("launchService")
@Transactional
public class LaunchServiceImpl implements LaunchService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private LaunchDao launchDao;
    @Autowired
    private LoginService loginService;

    @Override
    public void saveQuestion(Question question, HttpServletRequest request) {
        SaveSession user = loginService.checkCookie(request);
        question.setAuthor(user.getId());
        question.setUsername(user.getName());
        question.setCreatetime(new Date());
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

    @Override
    public List<Question> findAllQuestionByLimi(Integer page, Integer size, Model model) {
        Page pagetext = new Page();
        //查询总条数
        Integer count = launchDao.findAllQuestionCount();
        pagetext.setData(page,size,count);
        model.addAttribute("pages",pagetext);
        //把当前页和总页数传进domain工具中进行判断处理
        return launchDao.findAllQuestionByLimi(pagetext.getBeginpage(),pagetext.getSize());
    }

    /**
     * 更新评论数
     * @param id
     */
    @Override
    public void updateCommentCount(Integer id) {
        //更新评论数
        launchDao.updateCommentCount(id);
    }

    @Override
    public Integer findAllCommentById(Integer id) {
        return launchDao.findAllCommentById(id);
    }

}
