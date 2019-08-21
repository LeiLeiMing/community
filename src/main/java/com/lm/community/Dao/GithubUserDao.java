package com.lm.community.Dao;

import com.lm.community.Domain.GithubUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GithubUserDao {
    /**
     * 把登录过的当前用户信息存进数据库
     * @param githubUser
     */
    @Insert("insert into githubuser (name,bio,avatar_url) values(#{name},#{bio},#{avatar_url})")
    void saveGithubUser(GithubUser githubUser);
}
