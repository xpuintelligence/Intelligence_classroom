package com.smart.mapper;

import com.smart.pojo.TbAnswerQuestion;
import com.smart.pojo.TbAnswerQuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAnswerQuestionMapper {
    long countByExample(TbAnswerQuestionExample example);

    int deleteByExample(TbAnswerQuestionExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbAnswerQuestion record);

    int insertSelective(TbAnswerQuestion record);

    List<TbAnswerQuestion> selectByExample(TbAnswerQuestionExample example);

    TbAnswerQuestion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbAnswerQuestion record, @Param("example") TbAnswerQuestionExample example);

    int updateByExample(@Param("record") TbAnswerQuestion record, @Param("example") TbAnswerQuestionExample example);

    int updateByPrimaryKeySelective(TbAnswerQuestion record);

    int updateByPrimaryKey(TbAnswerQuestion record);
}