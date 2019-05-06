package com.smart.mapper;

import com.smart.pojo.TbCourseitemPpt;
import com.smart.pojo.TbCourseitemPptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbCourseitemPptMapper {
    long countByExample(TbCourseitemPptExample example);

    int deleteByExample(TbCourseitemPptExample example);

    int deleteByPrimaryKey(String courseitemId);

    int insert(TbCourseitemPpt record);

    int insertSelective(TbCourseitemPpt record);

    List<TbCourseitemPpt> selectByExample(TbCourseitemPptExample example);

    TbCourseitemPpt selectByPrimaryKey(String courseitemId);

    int updateByExampleSelective(@Param("record") TbCourseitemPpt record, @Param("example") TbCourseitemPptExample example);

    int updateByExample(@Param("record") TbCourseitemPpt record, @Param("example") TbCourseitemPptExample example);

    int updateByPrimaryKeySelective(TbCourseitemPpt record);

    int updateByPrimaryKey(TbCourseitemPpt record);
}