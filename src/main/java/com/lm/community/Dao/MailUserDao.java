package com.lm.community.Dao;

import com.lm.community.Domain.MailUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MailUserDao {

    /**
     * 保存邮箱用户
     * @param mailUser
     */
    @Insert("insert into mailuser (name,password,avatar_url) values(#{name},#{password},#{avatar_url})")
    public void registerMailUser(MailUser mailUser);

    /**
     * 查看该用户
     */
    @Select("select * from mailuser where name = #{name}")
    MailUser checkUser(String name);

    /**
     * 获取登录用户信息
     */
    @Select("select * from mailuser where name = #{name} and password = #{password}")
    MailUser findLoginUser(String name,String password);
}
