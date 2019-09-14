package com.lm.community.Dao;

import com.lm.community.Domain.SaveSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginDao {
    /**
     * 根据token查询用户
     * @param token
     * @return
     */
    @Select("select * from savesession where token = #{token}")
    SaveSession findSessionByToken(@Param(value = "token")String token);
}
