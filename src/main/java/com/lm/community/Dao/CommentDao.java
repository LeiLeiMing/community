package com.lm.community.Dao;

import com.lm.community.Domain.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    @Insert("insert into comment (questionid,comment,commentor,commenttime) values(#{questionid},#{comment},#{commentor},#{commenttime})")
    void saveComment(Comment comment);

    @Select("select * from comment  where questionid = #{id} order by id desc")
    @Results(id = "comment", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "questionid",column = "questionid"),
            @Result(property = "comment",column = "comment"),
            @Result(property = "commentor",column = "commentor"),
            @Result(property = "commenttime",column = "commenttime"),
            @Result(property = "commentcount",column = "commentcount"),
            @Result(property = "read",column = "read"),
            @Result(property = "user",column = "commentor",
                    one = @One(select = "com.lm.community.Dao.SaveSessionDao.findCommentorById",fetchType = FetchType.DEFAULT)),
            @Result(property = "recomment",column = "id",
                    many = @Many(select = "com.lm.community.Dao.RecommentDao.findAllRecommentById",fetchType = FetchType.DEFAULT)),
            @Result(property = "recommentcount",column = "id",
                    one = @One(select = "com.lm.community.Dao.RecommentDao.findAllRecommentCount",fetchType = FetchType.DEFAULT)),
    })
    List<Comment> findAllCommentById(Integer commentor);

    /**
     * 查询评论总数
     * @param id
     * @return
     */
    @Select("select count(1) from comment where questionid = #{id}")
    Integer findAllCommentCount(Integer id);

    /**
     * 查询所有未读评论，不包括二级评论
     * @return
     */
    @Select(" select * from comment where  questionid  IN \n" +
            "\t (select id from question where author  in (select id from savesession where name = #{name}) )\n" +
            "\t and commentor not in (select id from savesession where name =#{name}) order by id desc")
    @Results(id = "norreadcomment", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "questionid",column = "questionid"),
            @Result(property = "comment",column = "comment"),
            @Result(property = "commentor",column = "commentor"),
            @Result(property = "commenttime",column = "commenttime"),
            @Result(property = "commentcount",column = "commentcount"),
            @Result(property = "read",column = "read"),
            @Result(property = "user",column = "commentor",
                    one = @One(select = "com.lm.community.Dao.SaveSessionDao.findCommentorById",fetchType = FetchType.DEFAULT)),
            @Result(property = "recommentcount",column = "id",
                    one = @One(select = "com.lm.community.Dao.RecommentDao.findAllRecommentCount",fetchType = FetchType.DEFAULT)),
            @Result(property = "question",column = "questionid",
                    one = @One(select = "com.lm.community.Dao.PageDao.findQuestionById",fetchType = FetchType.DEFAULT)),
    })
    List<Comment> findAllNotReadComment(String name);

    /**
     * 查询所有未读一级评论总数
     * @return
     */
    @Select(" select count(1) from comment where comment.read=1 and  questionid  IN \n" +
            "\t (select id from question where author  in (select id from savesession where name =#{name}) )\n" +
            "\t and commentor not in (select id from savesession where name = #{name})")
    Integer finsAllNotReadCommentCount(String name);

    /**
     * 把一级评论标为已读
     * @param id
     */
    @Update("update comment set comment.read = 0 where id = #{id} ")
    void markReadComment(Integer id);

}
