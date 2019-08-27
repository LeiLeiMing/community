package com.lm.community.Dao;

import com.lm.community.Domain.SaveSession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SaveSessionDao {
    @Insert("insert into savesession (name,token,time,avatar_url) values(#{name},#{token},#{time},#{avatar_url})")
    void saveSession(SaveSession saveSession);

    @Select("select * from savesession where id = #{author}")
    SaveSession findUserById(Integer author);

    @Select("select * from savesession where id = #{commentor}")
    SaveSession findCommentorById(Integer commentor);
}
