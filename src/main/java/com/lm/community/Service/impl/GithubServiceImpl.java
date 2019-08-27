package com.lm.community.Service.impl;

import com.lm.community.Dao.GithubUserDao;
import com.lm.community.Domain.GithubUser;
import com.lm.community.Service.GithubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("githubUserService")
@Transactional
public class GithubServiceImpl implements GithubUserService {
    @Autowired
    private GithubUserDao githubUserDao;
    @Override
    public void saveGithubUser(GithubUser githubUser) {
        githubUserDao.saveGithubUser(githubUser);
    }
}
