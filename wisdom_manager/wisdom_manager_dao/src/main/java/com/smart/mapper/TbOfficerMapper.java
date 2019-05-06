package com.smart.mapper;

import com.smart.pojo.TbOfficer;
import com.smart.pojo.TbOfficerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOfficerMapper {
    long countByExample(TbOfficerExample example);

    int deleteByExample(TbOfficerExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbOfficer record);

    int insertSelective(TbOfficer record);

    List<TbOfficer> selectByExample(TbOfficerExample example);

    TbOfficer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbOfficer record, @Param("example") TbOfficerExample example);

    int updateByExample(@Param("record") TbOfficer record, @Param("example") TbOfficerExample example);

    int updateByPrimaryKeySelective(TbOfficer record);

    int updateByPrimaryKey(TbOfficer record);
}