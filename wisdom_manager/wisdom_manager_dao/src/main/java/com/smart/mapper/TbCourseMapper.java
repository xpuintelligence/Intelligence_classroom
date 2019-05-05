package com.smart.mapper;

import com.smart.pojo.TbCourse;
import com.smart.pojo.TbCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCourseMapper {
    long countByExample(TbCourseExample example);

    int deleteByExample(TbCourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbCourse record);

    int insertSelective(TbCourse record);

    List<TbCourse> selectByExample(TbCourseExample example);

    TbCourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbCourse record, @Param("example") TbCourseExample example);

    int updateByExample(@Param("record") TbCourse record, @Param("example") TbCourseExample example);

    int updateByPrimaryKeySelective(TbCourse record);

    int updateByPrimaryKey(TbCourse record);
}