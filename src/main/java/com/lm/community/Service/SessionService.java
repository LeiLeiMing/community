package com.lm.community.Service;

import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;

import java.util.List;

public interface SessionService {
    void saveSession(SaveSession saveSession);

    SaveSession findSavesession(String token);

}
