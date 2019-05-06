package com.smart.mapper;

import com.smart.pojo.TbProfession;
import com.smart.pojo.TbProfessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProfessionMapper {
    long countByExample(TbProfessionExample example);

    int deleteByExample(TbProfessionExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbProfession record);

    int insertSelective(TbProfession record);

    List<TbProfession> selectByExample(TbProfessionExample example);

    TbProfession selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbProfession record, @Param("example") TbProfessionExample example);

    int updateByExample(@Param("record") TbProfession record, @Param("example") TbProfessionExample example);

    int updateByPrimaryKeySelective(TbProfession record);

    int updateByPrimaryKey(TbProfession record);
}