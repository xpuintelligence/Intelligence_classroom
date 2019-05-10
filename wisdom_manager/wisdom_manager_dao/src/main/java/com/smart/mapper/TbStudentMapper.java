package com.smart.mapper;

import com.smart.pojo.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbStudentMapper {
    long countByExample(TbStudentExample example);

    int deleteByExample(TbStudentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbStudent record);

    int insertSelective(TbStudent record);

    List<TbStudent> selectByExample(TbStudentExample example);

    TbStudent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbStudent record, @Param("example") TbStudentExample example);

    int updateByExample(@Param("record") TbStudent record, @Param("example") TbStudentExample example);

    int updateByPrimaryKeySelective(TbStudent record);

    int updateByPrimaryKey(TbStudent record);

//    自己添加的方法
    List<WCourseInfo> selectWCourseInfo(@Param("classId")String classId , @Param("start") String start , @Param("end") String end);
    StudentInfo selectStudentInfo(String wexin_id);//用openId查
    StudentInfo selectStudentIdAndPassword(@Param("id") String id, @Param("password") String password);
    AttendanceItem selectAttendanceItem(@Param("studentId") String studentId, @Param("start") String start, @Param("end") String end);
}