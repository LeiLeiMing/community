package com.lm.community.Dao;

import com.lm.community.Domain.Recomment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecommentDao {

    @Select("select * from recomment where commentid = #{id}")
    @Results(id = "comment", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "recommentor",column = "recommentor"),
            @Result(property = "recomment",column = "recomment"),
            @Result(property = "recommenttime",column = "recommenttime"),
            @Result(property = "commentorid",column = "commentorid"),
            @Result(property = "commentid",column = "commentid"),
            @Result(property = "questionid",column = "questionid"),
            @Result(property = "user",column = "recommentor",
                    one = @One(select = "com.lm.community.Dao.SaveSessionDao.findCommentorById",fetchType = FetchType.DEFAULT)),
    })
    List<Recomment> findAllRecommentById(Integer id);

    @Select("select count(1) from recomment where commentid = #{id}")
    Integer findAllRecommentCount(Integer id);

    @Insert("insert into recomment (recommentor,recomment,recommenttime,commentid,commentorid,questionid) " +
            " values(#{recommentor},#{recomment},#{recommenttime},#{commentid},#{commentorid},#{questionid})")
    void saveRecomment(Recomment recomment);
}
