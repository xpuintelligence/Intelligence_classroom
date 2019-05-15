package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.*;
import com.smart.service.CourseService;
import com.smart.service.StudentAttendanceService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    //定义时间格式
    @Value("yyyy-MM-dd HH:mm:ss")
    private String DATE_FORMAT_TOSTRING ;

    @Autowired
    private CourseService courseServiceImpl;

    @Autowired
    private TbStudentMapper tbStudentMapper;
    /**
     * 通过起始时间获取某一个班的某一节课具体一段时间的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param classId 查询的学生信息
     * @param courseId 某一节课的id
     * @return
     */
    @Override
    public WisdomResult getSpellTimeAttendance(String start, String end, String classId, String courseId) {
        DateTime startTime = DateUtils.stringToDatetime(start);
        DateTime endTime = DateUtils.stringToDatetime(end);
        //获得到这段时间的课程的考勤信息
        List<AttendanceItem> attendanceItems = tbStudentMapper.selectAttendanceItemByClassCourseStartEnd(classId,
                courseId, startTime.toString(DATE_FORMAT_TOSTRING), endTime.toString(DATE_FORMAT_TOSTRING));
        return WisdomResult.ok(attendanceItems);

    }

    /**
     * 通过起始时间与课程id查询这段时间中某位学生的某一节课的考勤情况
     * @param start 开始时间
     * @param end 结束时间
     * @param studentInfo 学生信息
     * @param courseId 课程id
     */
    @Override
    public WisdomResult getSpellTimeAttendanceOfOneCourse(String start, String end, StudentInfo studentInfo, String courseId) {
        DateTime startTime = DateUtils.stringToDatetime(start);
        DateTime endTime = DateUtils.stringToDatetime(end);
        //获取这段时间的考勤信息
        List<AttendanceItem> attendanceItems = tbStudentMapper.selectAttendanceItemByStudentCourseStartEnd(studentInfo.getId(), courseId, startTime.toString(DATE_FORMAT_TOSTRING),
                endTime.toString(DATE_FORMAT_TOSTRING));
        //返回查询数据
        return WisdomResult.ok(attendanceItems);
    }

    /**
     * 上一个方法的重载
     */
    @Override
    public WisdomResult getSpellTimeAttendanceOfOneCourse(DateTime start, DateTime end, StudentInfo studentInfo, String courseId) {
        //获取这段时间的考勤信息
        List<AttendanceItem> attendanceItems = tbStudentMapper.selectAttendanceItemByStudentCourseStartEnd(studentInfo.getId(),
                courseId,start.toString(DATE_FORMAT_TOSTRING),end.toString(DATE_FORMAT_TOSTRING));
        //返回查询数据
        return WisdomResult.ok(attendanceItems);
    }

    /**
     * 本周某一节课的课程考勤
     * @param studentInfo 学生信息
     * @param courseId  某一节课程的id
     */
    @Override
    public WisdomResult getThisWeekAttendanceOfOneCourse(StudentInfo studentInfo, String courseId) {
        //获取一下当前的事件
        DateTime currentTime = DateUtils.getCurrentTime();
        //计算出本周为该学期的第几个星期
        Integer weekInThisSemester = courseServiceImpl.dayOfWeekInThisSemester(currentTime);
        //为本学期的第几个月
        Integer monthInThisSemester = courseServiceImpl.dayOfMonthInThisSemester(currentTime);
        //使用当前时间推出本周的时间区间
        Map<String, DateTime> map = courseServiceImpl.getNWeekInThisSemester(weekInThisSemester);
        return getSpellTimeAttendanceOfOneCourse(map.get("start"),map.get("end"),studentInfo,courseId);
    }

    /**
     * 上周某一节课的课程考勤
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    @Override
    public WisdomResult getLastWeekAttendanceOfOneCourse(StudentInfo studentInfo, String courseId) {
        //获取一下当前的事件
        DateTime currentTime = DateUtils.getCurrentTime();
        //计算出本周为该学期的第几个星期
        Integer weekInThisSemester = courseServiceImpl.dayOfWeekInThisSemester(currentTime);
        //使用当前时间推出上周的时间区间
        Map<String, DateTime> map = courseServiceImpl.getNWeekInThisSemester(weekInThisSemester-1);
        return getSpellTimeAttendanceOfOneCourse(map.get("start"),map.get("end"),studentInfo,courseId);
    }

    /**
     * 本月某一节课的课程考勤
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    @Override
    public WisdomResult getThisMonthAttendanceOfOneCourse(StudentInfo studentInfo, String courseId) {
        //获取一下当前的事件
        DateTime currentTime = DateUtils.getCurrentTime();
        //为本学期的第几个月
        Integer monthInThisSemester = courseServiceImpl.dayOfMonthInThisSemester(currentTime);
        //使用当前时间推出本本的时间区间
        Map<String, DateTime> map = courseServiceImpl.getNMonthInThisSemester(monthInThisSemester);
        return getSpellTimeAttendanceOfOneCourse(map.get("start"),map.get("end"),studentInfo,courseId);
    }

    /**
     * 上一个月某一节课的课程考勤
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    @Override
    public WisdomResult getLastMonthAttendanceOfOneCourse(StudentInfo studentInfo, String courseId) {
        //获取一下当前的事件
        DateTime currentTime = DateUtils.getCurrentTime();
        //为本学期的第几个月
        Integer monthInThisSemester = courseServiceImpl.dayOfMonthInThisSemester(currentTime);
        //使用当前时间推出上月的时间区间
        Map<String, DateTime> map = courseServiceImpl.getNMonthInThisSemester(monthInThisSemester-1);
        return getSpellTimeAttendanceOfOneCourse(map.get("start"),map.get("end"),studentInfo,courseId);
    }

    /**
     * 查询出该学生某一门课程从开学到现在的考勤
     * @param studentInfo 学生信息
     * @param courseId 某一节课的id
     */
    @Override
    public WisdomResult getThisSemesterAttendanceOfOneCourse(StudentInfo studentInfo, String courseId) {
        //获取一下当前的事件
        DateTime currentTime = DateUtils.getCurrentTime();
        //获取开学时间
        DateTime opentime = courseServiceImpl.getOpentime();
        //返回查询结果
        return getSpellTimeAttendanceOfOneCourse(opentime,currentTime,studentInfo,courseId);
    }




    /**
     * 查询该学生一段时间内的所有课程的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param studentInfo 学生信息
     * @return
     */
    @Override
    public WisdomResult getASpellTimeAttendanceOfAllCoourse(String start, String end, StudentInfo studentInfo) {
        DateTime startDate = DateUtils.stringToDatetime(start);
        DateTime endDate = DateUtils.stringToDatetime(end);
        //查询数据结果
        return getASpellTimeAttendanceOfAllCoourse(startDate,endDate,studentInfo);
    }

    /**
     * 重载上面的方法
     */
    @Override
    public WisdomResult getASpellTimeAttendanceOfAllCoourse(DateTime start, DateTime end, StudentInfo studentInfo) {
        //查询数据结果
        List<AttendanceItem> attendanceItems = tbStudentMapper.selectAttendanceItem(studentInfo.getId(), start.toString(DATE_FORMAT_TOSTRING),
                end.toString(DATE_FORMAT_TOSTRING));
        return WisdomResult.ok(attendanceItems);
    }

    /**
     * 查询这个学生本星期所有课程的考勤信息
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getThisWeekAttendanceOfAllCoourse(StudentInfo studentInfo) {
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //查询这个星期的星期数
        Integer integer = courseServiceImpl.dayOfWeekInThisSemester(currentTime);
        //获取该星期的起使
        Map<String, DateTime> map = courseServiceImpl.getNWeekInThisSemester(integer);
        //返回结果
        return getASpellTimeAttendanceOfAllCoourse(map.get("start"),map.get("end"),studentInfo);
    }

    /**
     * 查询这个学生上个星期所有的课程的考勤信
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getLastWeekAttendanceOfAllCoourse(StudentInfo studentInfo) {
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //查询这个星期的星期数
        Integer integer = courseServiceImpl.dayOfWeekInThisSemester(currentTime);
        //获取上星期的起使
        Map<String, DateTime> map = courseServiceImpl.getNWeekInThisSemester(integer-1);
        //返回结果
        return getASpellTimeAttendanceOfAllCoourse(map.get("start"),map.get("end"),studentInfo);
    }

    /**
     * 查询这个学生本月的所有课程的考勤情况
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getThisMonthAttendanceOfAllCoourse(StudentInfo studentInfo) {
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //查询这个月为第几个月
        Integer integer = courseServiceImpl.dayOfMonthInThisSemester(currentTime);
        //获取这个月的起使
        Map<String, DateTime> map = courseServiceImpl.getNMonthInThisSemester(integer);
        //返回结果
        return getASpellTimeAttendanceOfAllCoourse(map.get("start"),map.get("end"),studentInfo);
    }

    /**
     * 查询这个学生生上个月的所有课程的考勤信息
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getLastMonthAttendanceOfAllCoourse(StudentInfo studentInfo) {
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //查询这个月为第几个月
        Integer integer = courseServiceImpl.dayOfMonthInThisSemester(currentTime);
        //获取上月的起使
        Map<String, DateTime> map = courseServiceImpl.getNMonthInThisSemester(integer-1);
        //返回结果
        return getASpellTimeAttendanceOfAllCoourse(map.get("start"),map.get("end"),studentInfo);
    }

    /**
     * 查询这个学生这个学期的所有课程的考勤信息
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getThisSemesterAttendanceOfAllCoourse(StudentInfo studentInfo) {
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //获取开学时间
        DateTime opentime = courseServiceImpl.getOpentime();
        //返回结果
        return getASpellTimeAttendanceOfAllCoourse(opentime,currentTime,studentInfo);
    }

    /**
     * *****************************************************************
     * 查询每天的考勤统计
     * 平均得分  几节课  哪一天  平均抬头率
     */

    /**
     * 传进来一段时间每天的大致考勤，将其在进行计算
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param list 大概考勤的链表
     */
    @Override
    public WisdomResult calculateDaysAttendanceCollectList(DateTime startTime, DateTime endTime, List<DaysAttendanceCollect> list) {
        //计算出天数
        int num = DateUtils.twoDayGap(startTime,endTime);
        //将短时间的数据进行格式化
        DaysAttendanceCollectStatistic daysAttendanceCollectStatistic =
                new DaysAttendanceCollectStatistic(num,list);
        return WisdomResult.ok(daysAttendanceCollectStatistic);
    }


    /**
     * 一段时间
     * @param studentInfo 学生信息
     * @param start 开始时间
     * @param end 结束时时间
     * @return
     */
    @Override
    public WisdomResult getASpellTimeProbableAttOfEveryday(StudentInfo studentInfo, String start, String end) {
        DateTime startTime = DateUtils.stringToDatetime(start);
        DateTime endTime = DateUtils.stringToDatetime(end);
        //调用mapper查询
        List<DaysAttendanceCollect> list = tbStudentMapper.selectDaysAttendanceCollectByIDStartEnd(studentInfo.getId(), startTime.toString(DATE_FORMAT_TOSTRING),
                endTime.toString(DATE_FORMAT_TOSTRING));
        return calculateDaysAttendanceCollectList(startTime, endTime, list);

    }

    /**
     * 上面的方法重载
     * @param studentInfo 学生信息
     * @param start 开始时间
     * @param end 结束时时间
     */
    @Override
    public WisdomResult getASpellTimeProbableAttOfEveryday(StudentInfo studentInfo, DateTime start, DateTime end) {
        //调用mapper查询
        List<DaysAttendanceCollect> list = tbStudentMapper.selectDaysAttendanceCollectByIDStartEnd(studentInfo.getId(), start.toString(DATE_FORMAT_TOSTRING),
                end.toString(DATE_FORMAT_TOSTRING));
        return calculateDaysAttendanceCollectList(start, end, list);
    }

    /**
     * 本周的大致考勤
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getThisWeekProbableAttOfEveryday(StudentInfo studentInfo) {
        //计算出当前日期
        DateTime currentTime = DateUtils.getCurrentTime();
        //计算出这周为第几周
        Integer integer = courseServiceImpl.dayOfWeekInThisSemester(currentTime);
        //计算出该周的起始
        Map<String, DateTime> map = courseServiceImpl.getNWeekInThisSemester(integer);
        //调用mapper查询对应考勤
        List<DaysAttendanceCollect> list = tbStudentMapper.selectDaysAttendanceCollectByIDStartEnd(studentInfo.getId(),map.get("start").toString(DATE_FORMAT_TOSTRING),
                currentTime.toString(DATE_FORMAT_TOSTRING));
        //返回结果
        return calculateDaysAttendanceCollectList(map.get("start"),currentTime,list);
    }

    /**
     * 上一周的
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getLastWeekProbableAttOfEveryday(StudentInfo studentInfo) {
        //计算出当前日期
        DateTime currentTime = DateUtils.getCurrentTime();
        //计算出这周为第几周
        Integer integer = courseServiceImpl.dayOfWeekInThisSemester(currentTime);
        //计算出该周的起始
        Map<String, DateTime> map = courseServiceImpl.getNWeekInThisSemester(integer-1);
        //调用mapper查询对应考勤
        List<DaysAttendanceCollect> list = tbStudentMapper.selectDaysAttendanceCollectByIDStartEnd(studentInfo.getId(),map.get("start").toString(DATE_FORMAT_TOSTRING),
                map.get("end").toString(DATE_FORMAT_TOSTRING));
        //返回结果
        return calculateDaysAttendanceCollectList(map.get("start"),map.get("end"),list);
    }

    /**
     * 计算这个月的
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getThisMonthProbableAttOfEveryday(StudentInfo studentInfo) {
        //计算出当前日期
        DateTime currentTime = DateUtils.getCurrentTime();
        //计算出这周为第几周
        Integer integer = courseServiceImpl.dayOfMonthInThisSemester(currentTime);
        //计算出该周的起始
        Map<String, DateTime> map = courseServiceImpl.getNMonthInThisSemester(integer);
        //调用mapper查询对应考勤
        List<DaysAttendanceCollect> list = tbStudentMapper.selectDaysAttendanceCollectByIDStartEnd(studentInfo.getId(),map.get("start").toString(DATE_FORMAT_TOSTRING),
                currentTime.toString(DATE_FORMAT_TOSTRING));
        //返回结果
        return calculateDaysAttendanceCollectList(map.get("start"),currentTime,list);
    }

    /**
     * 计算上个月的
     * @param studentInfo 学生信息
     * @return
     */
    @Override
    public WisdomResult getLastMonthProbableAttOfEveryday(StudentInfo studentInfo) {
        //计算出当前日期
        DateTime currentTime = DateUtils.getCurrentTime();
        //计算出这周为第几周
        Integer integer = courseServiceImpl.dayOfMonthInThisSemester(currentTime);
        //计算出该周的起始
        Map<String, DateTime> map = courseServiceImpl.getNMonthInThisSemester(integer-1);
        //调用mapper查询对应考勤
        List<DaysAttendanceCollect> list = tbStudentMapper.selectDaysAttendanceCollectByIDStartEnd(studentInfo.getId(),map.get("start").toString(DATE_FORMAT_TOSTRING),
                map.get("end").toString(DATE_FORMAT_TOSTRING));
        //返回结果
        return calculateDaysAttendanceCollectList(map.get("start"), map.get("end"),list);
    }

    /**
     * 重载下面的方法
     */
    @Override
    public WisdomResult getOneDayProbableAttOfEveryday(StudentInfo studentInfo, String oneday) {
        DateTime dateTime = DateUtils.stringToDatetime(oneday);
        return getOneDayProbableAttOfEveryday(studentInfo,dateTime);
    }

    /**
     * 计算出某一天的大概考勤
     * @param studentInfo 学生信息
     * @return
     */
    @Override
    public WisdomResult getOneDayProbableAttOfEveryday(StudentInfo studentInfo , DateTime oneday) {
        //将改天的时间区间计算出来
        Map<String, DateTime> map = DateUtils.formatSpellTime(oneday);
        //查询这段时间考勤信息
        return getASpellTimeProbableAttOfEveryday(studentInfo, map.get("start"), map.get("end"));
    }

    /**
     * 查询今天的
     * @param studentInfo 学生信息
     * @return
     */
    @Override
    public WisdomResult gettodayProbableAttOfEveryday(StudentInfo studentInfo) {
        //获取当前时间
        DateTime time = DateUtils.getCurrentTime();
        //计算出今天的时间区间
        Map<String, DateTime> map = DateUtils.formatSpellTime(time);
        //查询这段时间的考勤信息
        return getASpellTimeProbableAttOfEveryday(studentInfo,map.get("start"),time);
    }

    /**
     * 查询这个学生这个学期考勤信息
     * @param studentInfo 学生信息
     */
    @Override
    public WisdomResult getThisSemesterProbableAttOfEveryday(StudentInfo studentInfo) {
        //获取开学时间
        DateTime opentime = courseServiceImpl.getOpentime();
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //查询加返回
        return getASpellTimeProbableAttOfEveryday(studentInfo,opentime,currentTime);
    }



}
