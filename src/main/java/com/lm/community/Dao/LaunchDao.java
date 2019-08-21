package com.lm.community.Dao;

import com.lm.community.Domain.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LaunchDao {

    @Insert("insert into question (desction,createtime,author,tag,title) values(#{desction},#{createtime},#{author},#{tag},#{title})")
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
            @Result(property = "saveSession",column = "author",
                    many = @Many(select = "com.lm.community.Dao.SaveSessionDao.findUserById",fetchType = FetchType.DEFAULT)),
    })
    List<Question> findAllQuestion();
}
