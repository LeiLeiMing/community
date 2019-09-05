package com.lm.community.Service;

import com.lm.community.Domain.Question;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PageService {


    List<Question> findQuestionByUserId(Integer page,Integer size, Model model,HttpServletRequest request);

    Question findQuestionById(Integer id);

    public void updateViewCount(Integer id);

    List<Question> findSimleQuestion(String[] tags,Integer id);

    public void editQuestionById(Question question);

    List<Question> findHostQuestions();

    List<Question> findNewQuestion();

    List<Question> searchQuestion(String search);

    public void updateLikecount(Integer id);

    public Integer likecount(Integer questionid);
}
