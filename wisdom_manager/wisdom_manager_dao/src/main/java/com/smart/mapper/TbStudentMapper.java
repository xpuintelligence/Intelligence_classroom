package com.smart.mapper;

import com.smart.pojo.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbStudentMapper {
    long countByExample(TbStudentExample example);

    int deleteByExample(TbStudentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbStudent record);

    int insertSelective(TbStudent record);

    List<TbStudent> selectByExample(TbStudentExample example);

    TbStudent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbStudent record, @Param("example") TbStudentExample example);

    int updateByExample(@Param("record") TbStudent record, @Param("example") TbStudentExample example);

    int updateByPrimaryKeySelective(TbStudent record);

    int updateByPrimaryKey(TbStudent record);

//    自己添加的方法

    /**
     *  查询特定班级的一段时间内的课程
     * @param classId 查询的班级编号
     * @param start 开始时间
     * @param end 结束时间
     */
    List<WCourseInfo> selectWCourseInfo(@Param("classId")String classId , @Param("start") String start , @Param("end") String end);

    /**
     * 使用微信的openid查询学生用户信息
     * @param wexin_id 全网唯一的一个openid
     * @return 这个学生的个人信息
     */
    StudentInfo selectStudentInfo(String wexin_id);//用openId查

    /**
     * 通过账号密码学生账号
     * @param id 账号
     * @param password 密码
     * @return 学生的基本信息
     */
    StudentInfo selectStudentIdAndPassword(@Param("id") String id, @Param("password") String password);

    /**
     * 通过学生的id、开始时间，结束时间查询这段时间的考勤情况
     * @param studentId 学生id
     * @param start 开始时间
     * @param end 结束时间
     * @return 一个考勤链表
     */
    List<AttendanceItem> selectAttendanceItem(@Param("studentId") String studentId, @Param("start") String start, @Param("end") String end);
}