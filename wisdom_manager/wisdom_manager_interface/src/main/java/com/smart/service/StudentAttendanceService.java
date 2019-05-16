package com.smart.service;

import com.smart.pojo.DaysAttendanceCollect;
import com.smart.pojo.StudentInfo;
import com.smart.pojo.WisdomResult;
import org.joda.time.DateTime;

import java.util.List;

/**
 * 学生考勤接口
 */
public interface StudentAttendanceService {
    /**
     * 通过起始时间获取某一位学生的某一节课具体一段时间的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param classId 查询的学生信息
     * @param courseId 某一节课的id
     * @return 结果集
     */
    public WisdomResult getSpellTimeAttendance(String start , String end , String classId , String courseId);

    /**
     * 通过起始时间与课程id查询这段时间中某位学生的每一节课的考勤情况
     * @param start 开始时间
     * @param end 结束时间
     * @param studentInfo 学生信息
     * @param courseId 课程id
     */
    public WisdomResult getSpellTimeAttendanceOfOneCourse(String start , String end , StudentInfo studentInfo, String courseId);

    /**
     * 上一个方法的重载
     */
    public WisdomResult getSpellTimeAttendanceOfOneCourse(DateTime start , DateTime end , StudentInfo studentInfo, String courseId);
    /**
     * 查出本周的某一节课课程考勤信息
     * @param studentInfo 学生信息
     * @param courseId  某一节课程的id
     */
    public WisdomResult getThisWeekAttendanceOfOneCourse(StudentInfo studentInfo, String courseId);

    /**
     * 查出上一周的某一节课考勤信息
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    public WisdomResult getLastWeekAttendanceOfOneCourse(StudentInfo studentInfo, String courseId);

    /**
     * 查询本月的某一节课的考勤信息
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    public WisdomResult getThisMonthAttendanceOfOneCourse(StudentInfo studentInfo, String courseId);

    /**
     * 查出上一个月的某一节课的考勤信息
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    public WisdomResult getLastMonthAttendanceOfOneCourse(StudentInfo studentInfo, String courseId);

    /**
     * 查询这学期某一节课的考勤信息
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    public WisdomResult getThisSemesterAttendanceOfOneCourse(StudentInfo studentInfo, String courseId);

    /**
     * 查询该学生一段时间内所有课程的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param studentInfo 学生信息
     */
    public WisdomResult getASpellTimeAttendanceOfAllCoourse(String start,String end , StudentInfo studentInfo);

    /**
     * 重载上面的方法
     */
    public WisdomResult getASpellTimeAttendanceOfAllCoourse(DateTime start,DateTime end , StudentInfo studentInfo);

    /**
     * 查询这个学生该星期所有课程的考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getThisWeekAttendanceOfAllCoourse(StudentInfo studentInfo);

    /**
     * 查询这个学生上个星期所有的课程的考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getLastWeekAttendanceOfAllCoourse(StudentInfo studentInfo);

    /**
     * 查询这个学生本月的所有课程的考勤情况
     * @param studentInfo 学生信息
     */
    public WisdomResult getThisMonthAttendanceOfAllCoourse(StudentInfo studentInfo);

    /**
     * 查询这个学生生上个月的所有课程的考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getLastMonthAttendanceOfAllCoourse( StudentInfo studentInfo);

    /**
     * 查询这个学生这个学期的所有课程的考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getThisSemesterAttendanceOfAllCoourse(StudentInfo studentInfo);

    /**
     * *****************************************************************
     * 查询每天的考勤统计
     * 平均得分  几节课  哪一天  平均抬头率
     */

    /**
     * 二次计算一段时间的大概考勤
     */
    public WisdomResult calculateDaysAttendanceCollectList(DateTime startTime, DateTime endTime, List<DaysAttendanceCollect> list);

    /**
     * 通过学生id，查出一段时间内每一天的总体信息
     * @param studentInfo 学生信息
     * @param start 开始时间
     * @param end 结束时时间
     */
    public WisdomResult getASpellTimeProbableAttOfEveryday(StudentInfo studentInfo,String start,String end);

    /**
     * 上面的方法重载
     */
    public WisdomResult getASpellTimeProbableAttOfEveryday(StudentInfo studentInfo,DateTime start,DateTime end);

    /**
     * 查询出本周的每一天的大致考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getThisWeekProbableAttOfEveryday(StudentInfo studentInfo);

    /**
     * 查询出上一周的每一天的大致考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getLastWeekProbableAttOfEveryday(StudentInfo studentInfo);

    /**
     * 查询出这个月每一天的大致的考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getThisMonthProbableAttOfEveryday(StudentInfo studentInfo);

    /**
     * 查询出这个月每一天的大致考勤信息
     * @param studentInfo 学生信息
     * @return
     */
    public WisdomResult getLastMonthProbableAttOfEveryday(StudentInfo studentInfo);

    /**
     * 查询出某一天的大致考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getOneDayProbableAttOfEveryday(StudentInfo studentInfo,String oneday);

    /**
     * 查询出某一天的大致考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult getOneDayProbableAttOfEveryday(StudentInfo studentInfo,DateTime oneday);

    /**
     * 查询出今天的大致考勤信息
     * @param studentInfo 学生信息
     */
    public WisdomResult gettodayProbableAttOfEveryday(StudentInfo studentInfo);

    /**
     * 查询出这个学期每一天的考勤信息
     * @param page
     * @param size
     * @param studentInfo 学生信息
     */
    public WisdomResult getThisSemesterProbableAttOfEveryday(Integer page, Integer size, StudentInfo studentInfo);


}
