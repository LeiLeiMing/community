package com.lm.community.Service;

import com.lm.community.Domain.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LaunchService {
    void saveQuestion(Question question, HttpServletRequest request);

    List<Question> findAllQuestion();
}
