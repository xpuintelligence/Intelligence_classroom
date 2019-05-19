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
     * 查询特定班级的一段时间内的课程
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

    /**
     * 通过学号查询信息
     * @param id 学号
     * @return
     */
    StudentInfo selectStudentInfoById(String id);

    /**
     * 通过学生的id、课程的id、开始时间、结束时间查询这段时间的考勤情况
     * @param studentId 学生id
     * @param start 开始时间
     * @param end 结束时间
     * @return 一个考勤链表
     */
    List<AttendanceItem> selectAttendanceItemByStudentCourseStartEnd(@Param("studentId") String studentId,@Param("courseId") String courseId , @Param("start") String start, @Param("end") String end);

    /**
     * 通过班级id、课程id、开始时间和结束时间查询 这段时间，这个班的考勤情况
     * @param classId 班级id
     * @param courseId 课程id
     * @param start 开始
     * @param end 结束
     * @return
     */
    List<AttendanceItem> selectAttendanceItemByClassCourseStartEnd(@Param("classId") String classId,@Param("courseId") String courseId , @Param("start") String start, @Param("end") String end);

    /**
     * 通过学生id，开始时间，结束时间，查询每天的汇总情况
     * @param id 学生id
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    List<DaysAttendanceCollect> selectDaysAttendanceCollectByIDStartEnd(@Param("id")String id,@Param("start")String start,@Param("end")String end);

    /**
     * 通过学生id，开始时间，结束时间，查询所有考勤的汇总信息
     * @param id 学生id
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    StageAttendanceCollect queryStageAttendanceCollectByIdStartEnd(@Param("id")String id,@Param("start")String start,@Param("end")String end);

    /**
     * 通过课程id，开始时间，结束时间查询本课考勤的汇总信息
     * @param course_id 课程id
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    StageAttendanceCollect queryStageAttendanceCollectByCourseIdStartEnd(@Param("course_id")String course_id,@Param("start")String start,@Param("end")String end);

    /**
     * 查看今天有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     * @param start
     * @param end
     * @return
     */
    List<CheckAttendance> queryCheckAttendance(@Param("start")String start, @Param("end")String end, @Param("teacher_id")String teacher_id);

    /**
     * 根据这节课的id查询每个人的考勤状况
     * @param courseitem_id 这节课的id
     * @return
     */
    List<AttendanceCourseItem> queryAttendanceCourseItemByCourseitem_id(String courseitem_id);

    /**
     * 根据课程和学生，查询此学生在这门课的平均表现情况
     * @param student_id 学生id
     * @param course_id 课程id
     * @return
     */
    CourseAVGAttendance queryCourseAVGAttendance(@Param("student_id")String student_id, @Param("course_id")String course_id);
}