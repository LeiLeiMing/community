package com.lm.community.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TopicalDao {

    /**
     * 把questionid插入到topical中
     * @param questionid
     */
    @Insert("insert into topical (questionid,topicaltype) values(#{questionid},#{topical})")
    public void addTopical(Integer questionid,Integer topical);
}
