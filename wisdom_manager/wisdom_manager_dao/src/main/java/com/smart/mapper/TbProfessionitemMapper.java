package com.smart.mapper;

import com.smart.pojo.TbProfessionitem;
import com.smart.pojo.TbProfessionitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProfessionitemMapper {
    long countByExample(TbProfessionitemExample example);

    int deleteByExample(TbProfessionitemExample example);

    int deleteByPrimaryKey(String professionId);

    int insert(TbProfessionitem record);

    int insertSelective(TbProfessionitem record);

    List<TbProfessionitem> selectByExample(TbProfessionitemExample example);

    TbProfessionitem selectByPrimaryKey(String professionId);

    int updateByExampleSelective(@Param("record") TbProfessionitem record, @Param("example") TbProfessionitemExample example);

    int updateByExample(@Param("record") TbProfessionitem record, @Param("example") TbProfessionitemExample example);

    int updateByPrimaryKeySelective(TbProfessionitem record);

    int updateByPrimaryKey(TbProfessionitem record);
}