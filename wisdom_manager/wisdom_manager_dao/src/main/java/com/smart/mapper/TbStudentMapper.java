package com.smart.mapper;

import com.smart.pojo.TbStudent;
import com.smart.pojo.TbStudentExample;
import java.util.List;

import com.smart.pojo.WCourseInfo;
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

    List<WCourseInfo> selectWCourseInfo(@Param("classId")String classId , @Param("start") String start , @Param("end") String end);
}