package com.smart.mapper;

import com.smart.pojo.TbCourseitem;
import com.smart.pojo.TbCourseitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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