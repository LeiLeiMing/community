package com.lm.community.Dao;

import com.lm.community.Domain.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PageDao {
    @Select("select count(1) from question where username = #{username}")
    Integer findQuestionCountByUid(String username);

    @Select("select * from question where id = #{id}")
    @Results(id = "questions", value = {
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
    Question findQuestionById(Integer id);

    /**
     * 阅读次数
     * @param id
     */
    @Update("update question set viewcount = viewcount + 1 where id = #{id}")
    void updateViewCount(Integer id);

    /**
     * 查找相似问题
     * @param tag
     * @return
     */
    @Select(" select * FROM question where tag like CONCAT('%',#{tag},'%') and id not in (#{id})")
    @Results(id = "simplequestions", value = {
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
    List<Question> findSimleQuestion(String tag,Integer id);
}
