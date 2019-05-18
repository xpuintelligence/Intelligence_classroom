package com.smart.mapper;

import com.smart.pojo.AttendanceSituation;
import com.smart.pojo.TbTeacher;
import com.smart.pojo.TbTeacherExample;
import java.util.List;

import com.smart.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import javax.xml.stream.events.EndDocument;

public interface TbTeacherMapper {
    long countByExample(TbTeacherExample example);

    int deleteByExample(TbTeacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbTeacher record);

    int insertSelective(TbTeacher record);

    List<TbTeacher> selectByExample(TbTeacherExample example);

    TbTeacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbTeacher record, @Param("example") TbTeacherExample example);

    int updateByExample(@Param("record") TbTeacher record, @Param("example") TbTeacherExample example);

    int updateByPrimaryKeySelective(TbTeacher record);

    int updateByPrimaryKey(TbTeacher record);

    //自己添加的方法
    /**
     * 使用微信的openid查询老师用户信息
     * @param wexin_id 全网唯一的一个openid
     * @return 这个老师的个人信息
     */
    TeacherInfo selectTeacherInfo(String wexin_id);//用openId查

    /**
     * 通过账号密码老师账号
     * @param id 账号
     * @param password 密码
     * @return 学生的基本信息
     */
    TeacherInfo selectTeacherIdAndPassword(@Param("id") String id, @Param("password") String password);

    /**
     * 通过老师查询信息
     * @param id 学号
     * @return
     */
    TeacherInfo selectTeacherInfoById(String id);

    /**
     * 查询一段时间的缺勤情况
     * @param status 查勤的状态
     * @param start 开始时间(可以和下者同时为null)
     * @param end 结束时间(可以和上着同时为null)
     * @param courseitem_id 哪节课(可以为null，为null是必须有start和end)
     * @return
     */
    List<AttendanceSituation> queryAttendanceSituation(@Param("status")String status, @Param("start")String start, @Param("end")String end, @Param("courseitem_id")String courseitem_id);
}