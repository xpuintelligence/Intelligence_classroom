package com.smart.mapper;

import com.smart.pojo.TbCollegeitem;
import com.smart.pojo.TbCollegeitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCollegeitemMapper {
    long countByExample(TbCollegeitemExample example);

    int deleteByExample(TbCollegeitemExample example);

    int deleteByPrimaryKey(String collegeId);

    int insert(TbCollegeitem record);

    int insertSelective(TbCollegeitem record);

    List<TbCollegeitem> selectByExample(TbCollegeitemExample example);

    TbCollegeitem selectByPrimaryKey(String collegeId);

    int updateByExampleSelective(@Param("record") TbCollegeitem record, @Param("example") TbCollegeitemExample example);

    int updateByExample(@Param("record") TbCollegeitem record, @Param("example") TbCollegeitemExample example);

    int updateByPrimaryKeySelective(TbCollegeitem record);

    int updateByPrimaryKey(TbCollegeitem record);
}