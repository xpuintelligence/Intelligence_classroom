package com.smart.mapper;

import com.smart.pojo.TbCollege;
import com.smart.pojo.TbCollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCollegeMapper {
    long countByExample(TbCollegeExample example);

    int deleteByExample(TbCollegeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbCollege record);

    int insertSelective(TbCollege record);

    List<TbCollege> selectByExample(TbCollegeExample example);

    TbCollege selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbCollege record, @Param("example") TbCollegeExample example);

    int updateByExample(@Param("record") TbCollege record, @Param("example") TbCollegeExample example);

    int updateByPrimaryKeySelective(TbCollege record);

    int updateByPrimaryKey(TbCollege record);
}