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

    @Select("select * from question where id = #{id} ")
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
    @Select(" select * FROM question where tag like CONCAT('%',#{tag},'%') and id not in (#{id}) order by id desc")
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
            @Result(property = "username",column = "username"),
            @Result(property = "saveSession",column = "author",
                    many = @Many(select = "com.lm.community.Dao.SaveSessionDao.findUserById",fetchType = FetchType.DEFAULT)),
    })
    List<Question> findSimleQuestion(String tag,Integer id);

    /**
     * 保存编辑好的问题
     * @param question
     */
    @Update("update question set title = #{title},desction = #{desction},tag = #{tag} where id = #{id}")
    void editQuestionById(Question question);

    /**
     * 热门问题
     */
    @Select("select * from question  order by viewcount desc limit 0,10")
    List<Question> findHostQuestions();

    /**
     * 最新问题
     * @return
     */
    @Select("select * from question order by createtime desc limit 0,10")
    List<Question> findNewQuestion();

    @Select("select * from question where title like CONCAT('%',#{search},'%') or tag like CONCAT('%',#{search},'%') or username like CONCAT('%',#{search},'%')")
    @Results(id = "searchquestion", value = {
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
    List<Question> searchQuestion(String search);

    /**
     * 更新点赞数
     * @param id
     */
    @Update("update question set likecount = likecount+1 where id = #{id}")
    public void updateLikecount(Integer id);

    /**
     * 点赞总数
     */
    @Select("select likecount from question where id =#{questionid}")
    public Integer likecount(Integer questionid);

    @Select("select * from question  order by viewcount desc limit #{page},#{size}")
    @Results(id = "limithotquestion", value = {
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
    List<Question> findAllHotQuestionByLimit(@Param(value = "page")Integer page,@Param(value = "size")Integer size);

    @Select("select count(1) from question where viewcount != 0")
    public Integer findHotQuestionCount();

    /**
     * 查询该用户最后一次插入的数据
     * @param name
     * @return
     */
    @Select("select * from question where username = #{name} order  by id desc limit 1 ")
    public Question findLastQuestion(String name);

    @Select("select * from question where id in(select questionid from topical where topical.topicaltype = #{topical}) LIMIT #{page},#{size}")
    @Results(id = "limittopicalquestion", value = {
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
    List<Question> findAllTopicalQuestionByLimit(@Param(value = "page")Integer page,@Param(value = "size")Integer size,@Param(value = "topical")Integer topical);

    @Select("select count(*) from question where id in (select questionid from topical where topicaltype = #{topical})")
    public Integer findTopicalCountQuestion(Integer topical);

}


