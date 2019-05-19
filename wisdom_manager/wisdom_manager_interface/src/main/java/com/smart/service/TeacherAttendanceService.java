package com.smart.service;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.TeacherInfo;
import com.smart.pojo.WisdomResult;
import org.joda.time.DateTime;

public interface TeacherAttendanceService {
    /**
     * 查询一段时间内缺勤的人
     */

    /**
     * 查询本节课多少个人缺勤
     */

    /**
     * 查询一段时间内每节课的缺勤情况
     */

    /**
     * 查询本节课有多少人睡觉
     */

    /**
     * 查询一段时间内有有多少人睡觉
     */

    /**
     * 查询一段时间内每一节课多少人睡觉
     */


    /**
     * 查询整个学期该学生在具体某节课上的表现情况
     */
    public WisdomResult getStudentAttOfThisSemester(String courseItemId,String studentId);
    /**
     * 查看某一个学生的具体某一节课的信息
     */
    public WisdomResult getStudentAbsentOfOneCourse(String start , String end, String courseId,String studentId);

    /**
     * 查看某一个学生在这个学期中某一段时间的具体考勤
     */

    /**
     * 老师修改这节课如何考勤
     */

    /**
     * 查看今天上课的班级的考勤情况
     */
    public WisdomResult getCourseAttOfClassToday(String classId,String courseId);

    /**
     * ***********************************************************************************
     * 有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     */

    /**
     * 查看今天有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     */
    public WisdomResult getAttASpellTime(String start, String end, TeacherInfo teacherInfo);

    /**
     * 查看今天有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     */
    public WisdomResult getAttASpellTime(DateTime start, DateTime end, TeacherInfo teacherInfo);

    /**
     * 获得今天的考勤信息
     * 查看今天有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     */
    public WisdomResult getAttToday(TeacherInfo teacherInfo);

    /**
     * 获得这周的考勤信息
     * 查看这周有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     */
    public WisdomResult getAttThisWeek(TeacherInfo teacherInfo);

    /**
     * 获得这月的考勤信息
     * 查看这月有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     */
    public WisdomResult getAttThisMont(TeacherInfo teacherInfo);

    /**
     * 获得这学期的考勤信息
     * 查看这学期有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
     */
    public WisdomResult getAttThisSemester(TeacherInfo teacherInfo);

    /**
     * ***********************************************************************************
     */

    /**
     * 查询某一天的课程列表，有每一节课的时间等上述考勤信息
     */
    public WisdomResult getAttOfOneDay(String day ,TeacherInfo teacherInfo);

    /**
     * 查询某一节课所有学生的考勤信息,带排序
     */
    public WisdomResult getAttOfClass(String crouseItemId);

    /**
     * ***********************************************************************************
     */

    /**
     * 查看一个学生，在一门课里面这学期的考勤状态
     */
    public WisdomResult getStudentAttOfCourse(String studentId,String courseId);

    /**
     *
     */


}
