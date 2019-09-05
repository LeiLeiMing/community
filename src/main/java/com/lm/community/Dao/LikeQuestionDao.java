package com.lm.community.Dao;

import com.lm.community.Domain.Likequestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LikeQuestionDao {

    /**
     * 保存点赞信息
     */
    @Insert("insert into likequestion (questionid,likeuserid,status) values(#{questionid},#{likeuserid},#{status})")
    public void saveLike(Likequestion likequestion);

    /**
     * 查询该用户下对该文章的点赞情况
     */
    @Select("select * from likequestion where questionid = #{questionid} and likeuserid = #{likeuserid} ")
    Likequestion findLikeByUserAndQid(@Param(value = "questionid") Integer questionid, @Param(value = "likeuserid")Integer likeuserid);
}
