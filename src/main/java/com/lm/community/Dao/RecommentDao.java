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
            @Result(property = "read",column = "read"),
            @Result(property = "user",column = "recommentor",
                    one = @One(select = "com.lm.community.Dao.SaveSessionDao.findCommentorById",fetchType = FetchType.DEFAULT)),
    })
    List<Recomment> findAllRecommentById(Integer id);



    @Select("select count(1) from recomment where commentid = #{id}")
    Integer findAllRecommentCount(Integer id);

    @Insert("insert into recomment (recommentor,recomment,recommenttime,commentid,commentorid,questionid) " +
            " values(#{recommentor},#{recomment},#{recommenttime},#{commentid},#{commentorid},#{questionid})")
    void saveRecomment(Recomment recomment);

    /**
     * 查询所有未读二级评论总数
     * @return
     */
    @Select("\tselect count(1) from recomment where recomment.read = 1 and  questionid  IN \n" +
            "\t (select id from question where author  in (select id from savesession where name = #{name}) )\n" +
            "\t and recommentor not in (select id from savesession where name = #{name})")
    Integer findAllNotReadRecomment(String name);

    /**
     * 查询当前用户下的所有未读二级评论
     * @param name
     * @return
     */
    @Select("select * from recomment where   questionid  IN \n" +
            "\t (select id from question where author  in (select id from savesession where name = #{name}) )\n" +
            "\t and recommentor not in (select id from savesession where name = #{name})")
    @Results(id = "notreadrecomment", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "recommentor",column = "recommentor"),
            @Result(property = "recomment",column = "recomment"),
            @Result(property = "recommenttime",column = "recommenttime"),
            @Result(property = "commentorid",column = "commentorid"),
            @Result(property = "commentid",column = "commentid"),
            @Result(property = "questionid",column = "questionid"),
            @Result(property = "read",column = "read"),
            @Result(property = "user",column = "recommentor",
                    one = @One(select = "com.lm.community.Dao.SaveSessionDao.findCommentorById",fetchType = FetchType.DEFAULT)),
            @Result(property = "question",column = "questionid",
                    one = @One(select = "com.lm.community.Dao.PageDao.findQuestionById",fetchType = FetchType.DEFAULT)),
    })
    List<Recomment> findAllNotRecommentCount(String name);

    /**
     * 二级评论标为已读
     * @param id
     */
    @Update("update recomment set recomment.read = 0 where id =#{id}")
    void markReadRecomment(Integer id);
}
