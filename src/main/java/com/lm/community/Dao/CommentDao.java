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

}
