package com.lm.community.Dao;

import com.lm.community.Domain.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LaunchDao {

    @Insert("insert into question (desction,createtime,author,tag,title,username) values(#{desction},#{createtime},#{author},#{tag},#{title},#{username})")
    void saveQuestion(Question question);

    @Select("select * from question")
    @Results(id = "user", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "desction",column = "desction"),
            @Result(property = "createtime",column = "createtime"),
            @Result(property = "modiftime",column = "modiftime"),
            @Result(property = "author",column = "author"),
            @Result(property = "commentcount",column = "commentcount"),
            @Result(property = "viewcount",column = "viewcount"),
            @Result(property = "likecount",column = "likecount"),
            @Result(property = "tag",column = "tag"),
            @Result(property = "title",column = "title"),
            @Result(property = "username",column = "username"),
            @Result(property = "saveSession",column = "author",
                    many = @Many(select = "com.lm.community.Dao.SaveSessionDao.findUserById",fetchType = FetchType.DEFAULT)),
    })
    List<Question> findAllQuestion();

    @Select("select * from question limit #{page},#{size}")
    @Results(id = "limitquestion", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "desction",column = "desction"),
            @Result(property = "createtime",column = "createtime"),
            @Result(property = "modiftime",column = "modiftime"),
            @Result(property = "author",column = "author"),
            @Result(property = "commentcount",column = "commentcount"),
            @Result(property = "viewcount",column = "viewcount"),
            @Result(property = "likecount",column = "likecount"),
            @Result(property = "tag",column = "tag"),
            @Result(property = "title",column = "title"),
            @Result(property = "username",column = "username"),
            @Result(property = "saveSession",column = "author",
                    many = @Many(select = "com.lm.community.Dao.SaveSessionDao.findUserById",fetchType = FetchType.DEFAULT)),
    })
    List<Question> findAllQuestionByLimi(@Param(value = "page")Integer page,@Param(value = "size")Integer size);

    @Select("select * from question where username = #{username} limit #{page},#{size} ")
    @Results(id = "limitquestionbyname", value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "desction",column = "desction"),
            @Result(property = "createtime",column = "createtime"),
            @Result(property = "modiftime",column = "modiftime"),
            @Result(property = "author",column = "author"),
            @Result(property = "commentcount",column = "commentcount"),
            @Result(property = "viewcount",column = "viewcount"),
            @Result(property = "likecount",column = "likecount"),
            @Result(property = "tag",column = "tag"),
            @Result(property = "title",column = "title"),
            @Result(property = "username",column = "username"),
            @Result(property = "saveSession",column = "author",
                    many = @Many(select = "com.lm.community.Dao.SaveSessionDao.findUserById",fetchType = FetchType.DEFAULT)),
    })
    List<Question> findAllQuestionByLimitName(@Param(value = "username")String userName, @Param(value = "page")Integer page,@Param(value = "size")Integer size);

    @Select("select count(1) from question")
    Integer findAllQuestionCount();

    /**
     * 更新评论数
     */
    @Update("update  question set commentcount =  commentcount + 1 where id = #{id}")
    void updateCommentCount(@Param(value = "id") Integer id);

    @Select("select count(1) from comment where questionid = #{id}")
    Integer findAllCommentById(Integer id);
}
