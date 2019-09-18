package com.lm.community.Service;

import com.lm.community.Domain.Question;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LaunchService {
    void saveQuestion(Question question, HttpServletRequest request);

    List<Question> findAllQuestion();

    List<Question> findAllQuestionByLimi(Integer page,Integer size, Model model);

    void updateCommentCount(Integer id);

    Integer findAllCommentById(Integer id);

    public Question findQuestionById(Integer id);

    public Question findLastQuestion(String name);

    List<Question> findAllTopicalQuestionByLimi(Integer page,Integer size, Model model,Integer topical);

}
