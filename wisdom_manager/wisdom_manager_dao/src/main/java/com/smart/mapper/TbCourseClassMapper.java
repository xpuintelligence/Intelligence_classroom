package com.smart.mapper;

import com.smart.pojo.TbCourseClass;
import com.smart.pojo.TbCourseClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbCourseClassMapper {
    long countByExample(TbCourseClassExample example);

    int deleteByExample(TbCourseClassExample example);

    int deleteByPrimaryKey(@Param("courseId") String courseId, @Param("classId") String classId);

    int insert(TbCourseClass record);

    int insertSelective(TbCourseClass record);

    List<TbCourseClass> selectByExample(TbCourseClassExample example);

    int updateByExampleSelective(@Param("record") TbCourseClass record, @Param("example") TbCourseClassExample example);

    int updateByExample(@Param("record") TbCourseClass record, @Param("example") TbCourseClassExample example);
}