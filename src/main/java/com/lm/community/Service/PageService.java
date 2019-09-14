package com.lm.community.Service;

import com.lm.community.Domain.Question;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public abstract class PageService {


    public abstract List<Question> findQuestionByUserId(Integer page, Integer size, Model model, HttpServletRequest request);

    public abstract Question findQuestionById(Integer id);

    public abstract void updateViewCount(Integer id);

    public abstract List<Question> findSimleQuestion(String[] tags, Integer id);

    public abstract void editQuestionById(Question question);

    public abstract List<Question> findHostQuestions();

    public abstract List<Question> findNewQuestion();

    public abstract List<Question> searchQuestion(String search);

    public abstract void updateLikecount(Integer id);

    public abstract Integer likecount(Integer questionid);

    public abstract List<Question> findAllHotQuestionByLimit(Integer page, Integer size,Model model);

    public abstract Integer findHotQuestionCount();
}
