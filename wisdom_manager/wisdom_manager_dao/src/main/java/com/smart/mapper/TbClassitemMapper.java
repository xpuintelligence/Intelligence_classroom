package com.smart.mapper;

import com.smart.pojo.TbClassitem;
import com.smart.pojo.TbClassitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbClassitemMapper {
    long countByExample(TbClassitemExample example);

    int deleteByExample(TbClassitemExample example);

    int deleteByPrimaryKey(String classId);

    int insert(TbClassitem record);

    int insertSelective(TbClassitem record);

    List<TbClassitem> selectByExample(TbClassitemExample example);

    TbClassitem selectByPrimaryKey(String classId);

    int updateByExampleSelective(@Param("record") TbClassitem record, @Param("example") TbClassitemExample example);

    int updateByExample(@Param("record") TbClassitem record, @Param("example") TbClassitemExample example);

    int updateByPrimaryKeySelective(TbClassitem record);

    int updateByPrimaryKey(TbClassitem record);
}