package com.lm.community.Service;

import com.lm.community.Domain.MailUser;

public interface MailUserService {

    public void registerMailUser(MailUser mailUser);

    MailUser checkUser(String mail);

    MailUser findLoginUser(String password,String name);
}
