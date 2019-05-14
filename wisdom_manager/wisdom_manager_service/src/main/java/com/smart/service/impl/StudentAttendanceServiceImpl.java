package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.AttendanceItem;
import com.smart.pojo.StudentInfo;
import com.smart.pojo.WisdomResult;
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

}
