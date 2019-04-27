package com.smart.mapper;

import com.smart.pojo.TbCourseitem;
import com.smart.pojo.TbCourseitemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbCourseitemMapper {
    long countByExample(TbCourseitemExample example);

    int deleteByExample(TbCourseitemExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbCourseitem record);

    int insertSelective(TbCourseitem record);

    List<TbCourseitem> selectByExample(TbCourseitemExample example);

    TbCourseitem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbCourseitem record, @Param("example") TbCourseitemExample example);

    int updateByExample(@Param("record") TbCourseitem record, @Param("example") TbCourseitemExample example);

    int updateByPrimaryKeySelective(TbCourseitem record);

    int updateByPrimaryKey(TbCourseitem record);
}