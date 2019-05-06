package com.smart.mapper;

import com.smart.pojo.TbCourseTeacher;
import com.smart.pojo.TbCourseTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCourseTeacherMapper {
    long countByExample(TbCourseTeacherExample example);

    int deleteByExample(TbCourseTeacherExample example);

    int deleteByPrimaryKey(@Param("courseId") String courseId, @Param("teacherId") String teacherId);

    int insert(TbCourseTeacher record);

    int insertSelective(TbCourseTeacher record);

    List<TbCourseTeacher> selectByExample(TbCourseTeacherExample example);

    int updateByExampleSelective(@Param("record") TbCourseTeacher record, @Param("example") TbCourseTeacherExample example);

    int updateByExample(@Param("record") TbCourseTeacher record, @Param("example") TbCourseTeacherExample example);
}