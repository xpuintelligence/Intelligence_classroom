package com.smart.mapper;

import com.smart.pojo.TbClassroom;
import com.smart.pojo.TbClassroomExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TbClassroomMapper {
    long countByExample(TbClassroomExample example);

    int deleteByExample(TbClassroomExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbClassroom record);

    int insertSelective(TbClassroom record);

    List<TbClassroom> selectByExample(TbClassroomExample example);

    TbClassroom selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbClassroom record, @Param("example") TbClassroomExample example);

    int updateByExample(@Param("record") TbClassroom record, @Param("example") TbClassroomExample example);

    int updateByPrimaryKeySelective(TbClassroom record);

    int updateByPrimaryKey(TbClassroom record);
}