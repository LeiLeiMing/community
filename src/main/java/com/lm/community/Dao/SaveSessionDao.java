package com.lm.community.Dao;

import com.lm.community.Domain.SaveSession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SaveSessionDao {
    @Insert("insert into savesession (username,token,time) values(#{username},#{token},#{time})")
    void saveSession(SaveSession saveSession);
}
