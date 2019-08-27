package com.lm.community.Service.impl;

import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.SessionService;
import com.lm.community.Dao.SaveSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SaveSessionDao saveSessionDao;

    @Override
    public void saveSession(SaveSession saveSession) {
        saveSessionDao.saveSession(saveSession);
    }
}
