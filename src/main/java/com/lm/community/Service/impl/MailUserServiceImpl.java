package com.lm.community.Service.impl;

import com.lm.community.Dao.MailUserDao;
import com.lm.community.Domain.MailUser;
import com.lm.community.Service.MailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mailUserService")
@Transactional
public class MailUserServiceImpl implements MailUserService {

    @Autowired
    private MailUserDao mailUserDao;


    @Override
    public void registerMailUser(MailUser mailUser) {
        mailUserDao.registerMailUser(mailUser);
    }

    @Override
    public MailUser checkUser(String mail) {
        return mailUserDao.checkUser(mail);
    }

    @Override
    public MailUser findLoginUser(String password ,String name) {
        MailUser user = mailUserDao.findLoginUser(name);
        if(user!=null){
            return user;
        }
        return null;
    }
}
