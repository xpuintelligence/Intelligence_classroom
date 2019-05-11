package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.StudentInfo;
import com.smart.pojo.WCourseInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.CourseService;
import com.smart.service.StudentCourseService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class StudentCourseServiceImpl implements StudentCourseService, Serializable {
    @Autowired
    private CourseService courseService ;
    //定义时间格式
    @Value("yyyy-MM-dd HH:mm:ss")
    private String DATE_FORMAT_TOSTRING ;

    @Autowired
    private TbStudentMapper tbStudentMapper;
    /**
     * 获取一段区间内的课程
     * @param studentInfo 要查询的学生
     * @param begin 开始时间
     * @param end 结束时间
     * @return
     */
    @Override
    public WisdomResult getCourseOfSpellTime(StudentInfo studentInfo, DateTime begin, DateTime end) {
        List<WCourseInfo> wCourseInfos = tbStudentMapper.selectWCourseInfo(studentInfo.getClassId(), begin.toString(DATE_FORMAT_TOSTRING),
                end.toString(DATE_FORMAT_TOSTRING));
        return new WisdomResult(1,"true",wCourseInfos);
    }

    /**
     * @param begin 开始时间的字符串
     * @param end 结束时间的字符串
     */
    @Override
    public WisdomResult getCourseOfSpellTime(StudentInfo studentInfo, String begin, String end) {
        //进行格式转换
        DateTime startTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);
        //返回结果集
        return getCourseOfSpellTime(studentInfo,startTime,endTime);
    }

    @Override
    public WisdomResult getCourseOfSpellTime(StudentInfo studentInfo, Map<String, DateTime> map) {
        System.out.println(map);
        List<WCourseInfo> wCourseInfos = tbStudentMapper.selectWCourseInfo(studentInfo.getClassId(), map.get("start").toString(DATE_FORMAT_TOSTRING),
                map.get("end").toString(DATE_FORMAT_TOSTRING));
        return new WisdomResult(1,"true",wCourseInfos);
    }

    /**
     * 某一天的课程
     * @param studentInfo 需要查询的学生
     * @param datetime 某一天的时间
     * @return
     */
    @Override
    public WisdomResult getCourseOfDay(StudentInfo studentInfo, DateTime datetime) {
        //获取时间轴
        Map<String, DateTime> map = DateUtils.spellTime(datetime);
        //返回结果集
        return getCourseOfSpellTime(studentInfo,map);
    }

    /***
     * @param day 字符串形式的时间
     */
    @Override
    public WisdomResult getCourseOfDay(StudentInfo studentInfo, String day) {

        //进行格式转换
        DateTime dateTime = new DateTime(day);
        //返回结果集
        return getCourseOfDay(studentInfo,dateTime);
    }


    /**
     * 今天的课程
     */
    @Override
    public WisdomResult getCourseOfToday(StudentInfo studentInfo) {
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //获取时间段
        Map<String, DateTime> map = DateUtils.spellTime(currentTime);
        //返回结果集
        return new WisdomResult(1,"true",getCourseOfSpellTime(studentInfo,map));
    }

    /**
     * 这个学期的课程
     */
    @Override
    public WisdomResult getCourseOfThisSemester(StudentInfo studentInfo) {
        //获取开学时间
        DateTime opentime = courseService.getOpentime();
        //获取放假时间
        DateTime recessTime = courseService.getRecessTime();
        //获取返回值
        return getCourseOfSpellTime(studentInfo,opentime,recessTime);
    }

    /**
     * 第n周的课程
     */
    @Override
    public WisdomResult getCourseInXWeek(StudentInfo studentInfo, Integer n) {
        //获取第n周的开始时间与结束时间
        Map<String, DateTime> map = courseService.getNWeekInThisSemester(n);
        //获取结果
        return getCourseOfSpellTime(studentInfo,map);
    }

    /**
     * 获取这个星期的课程
     */
    @Override
    public WisdomResult getCourseThisWeek(StudentInfo studentInfo) {
        //获取这周为该学期的第几周
        Integer integer = courseService.dayOfWeekInThisSemester(DateUtils.getCurrentTime());
        //获取该周的开始时间与结束时间
        Map<String, DateTime> map = courseService.getNWeekInThisSemester(integer);

        //返回值
        return getCourseOfSpellTime(studentInfo,map);
    }

    /**
     * 获取该月的课程信息
     */
    @Override
    public WisdomResult getCourseThisMonth(StudentInfo studentInfo) {
        //获取本月为该学期地几个月
        Integer integer = courseService.dayOfMonthInThisSemester(DateUtils.getCurrentTime());
        //获取该月的起始时间
        Map<String, DateTime> map = courseService.getNMonthInThisSemester(integer);
        //返回结果
        return getCourseOfSpellTime(studentInfo,map);
    }

    /**
     * 获取该学期第x个月的课程信息
     */
    @Override
    public WisdomResult getCourseInXMonth(StudentInfo studentInfo, Integer n) {
        //获取第x个月的起始时间
        Map<String, DateTime> map = courseService.getNMonthInThisSemester(n);
        //返回结果
        return getCourseOfSpellTime(studentInfo,map);
    }

    /**
     * 该学生前n个月的课程信息
     */
    @Override
    public WisdomResult getCourseFrontOfXMonth(StudentInfo studentInfo, Integer n) {
        //获取该天前n个月的起始值
        Map<String, DateTime> map = DateUtils.subXMonthDay(DateUtils.getCurrentTime(), n);
        //返回结果
        return getCourseOfSpellTime(studentInfo,map);
    }

    /**
     * 获取该学生前n个星期的课程信息
     */
    @Override
    public WisdomResult getCourseFrontOfXWeek(StudentInfo studentInfo, Integer n) {
        //获取该天前n个星期的起始值
        Map<String, DateTime> map = DateUtils.subXWeekDay(DateUtils.getCurrentTime(), n);
        //返回结果集co
        return getCourseOfSpellTime(studentInfo,map);
    }

}
