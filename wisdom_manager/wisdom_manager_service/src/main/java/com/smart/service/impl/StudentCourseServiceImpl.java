package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.TbStudent;
import com.smart.pojo.WCourseInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.CourseService;
import com.smart.service.StudentCourseService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * @param tbStudent 要查询的学生
     * @param begin 开始时间
     * @param end 结束时间
     * @return
     */
    @Override
    public WisdomResult getCourseOfSpellTime(TbStudent tbStudent, DateTime begin, DateTime end) {
        List<WCourseInfo> wCourseInfos = tbStudentMapper.selectWCourseInfo(tbStudent.getClassId(), begin.toString(DATE_FORMAT_TOSTRING),
                end.toString(DATE_FORMAT_TOSTRING));
        return new WisdomResult(1,"true",wCourseInfos);
    }

    @Override
    public WisdomResult getCourseOfSpellTime(TbStudent tbStudent, Map<String, DateTime> map) {
        System.out.println(map);
        List<WCourseInfo> wCourseInfos = tbStudentMapper.selectWCourseInfo(tbStudent.getClassId(), map.get("first").toString(DATE_FORMAT_TOSTRING),
                map.get("end").toString(DATE_FORMAT_TOSTRING));
        return new WisdomResult(1,"true",wCourseInfos);
    }

    /**
     * 某一天的课程
     * @param tbStudent 需要查询的学生
     * @param day 某一天的时间
     * @return
     */
    @Override
    public WisdomResult getCourseOfDay(TbStudent tbStudent, DateTime day) {
        //获取时间轴
        Map<String, DateTime> map = DateUtils.spellTime(day);
        //返回结果集
        return getCourseOfSpellTime(tbStudent,map);
    }

    /**
     * 今天的课程
     * @param tbStudent
     * @return
     */
    @Override
    public WisdomResult getCourseOfToday(TbStudent tbStudent) {
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //获取时间段
        Map<String, DateTime> map = DateUtils.spellTime(currentTime);
        //返回结果集
        return new WisdomResult(1,"true",getCourseOfSpellTime(tbStudent,map));
    }

    /**
     * 这个学期的课程
     * @param tbStudent
     * @return
     */
    @Override
    public WisdomResult getCourseOfThisSemester(TbStudent tbStudent) {
        //获取开学时间
        DateTime opentime = courseService.getOpentime();
        //获取放假时间
        DateTime recessTime = courseService.getRecessTime();
        //获取返回值
        return getCourseOfSpellTime(tbStudent,opentime,recessTime);
    }

    /**
     * 第n周的课程
     */
    @Override
    public WisdomResult getCourseInXWeek(TbStudent tbStudent, Integer n) {
        //获取第n周的开始时间与结束时间
        Map<String, DateTime> map = courseService.getNWeekInThisSemester(n);
        //获取结果
        return getCourseOfSpellTime(tbStudent,map);
    }

    /**
     * 获取这个星期的课程
     * @param tbStudent
     * @return
     */
    @Override
    public WisdomResult getCourseThisWeek(TbStudent tbStudent) {
        //获取这周为该学期的第几周
        Integer integer = courseService.dayOfWeekInThisSemester(DateUtils.getCurrentTime());
        //获取该周的开始时间与结束时间
        Map<String, DateTime> map = courseService.getNWeekInThisSemester(integer);

        //返回值
        return getCourseOfSpellTime(tbStudent,map);
    }

    /**
     * 获取该月的课程信息
     */
    @Override
    public WisdomResult getCourseThisMonth(TbStudent tbStudent) {
        //获取本月为该学期地几个月
        Integer integer = courseService.dayOfMonthInThisSemester(DateUtils.getCurrentTime());
        //获取该月的起始时间
        Map<String, DateTime> map = courseService.getNMonthInThisSemester(integer);
        //返回结果
        return getCourseOfSpellTime(tbStudent,map);
    }

    /**
     * 获取该学期第x个月的课程信息
     */
    @Override
    public WisdomResult getCourseInXMonth(TbStudent tbStudent, Integer n) {
        //获取第x个月的起始时间
        Map<String, DateTime> map = courseService.getNMonthInThisSemester(n);
        //返回结果
        return getCourseOfSpellTime(tbStudent,map);
    }

    /**
     * 该学生前n个月的课程信息
     */
    @Override
    public WisdomResult getCourseFrontOfXMonth(TbStudent tbStudent, Integer n) {
        //获取该天前n个月的起始值
        Map<String, DateTime> map = DateUtils.subXMonthDay(DateUtils.getCurrentTime(), n);
        //返回结果
        return getCourseOfSpellTime(tbStudent,map);
    }

    /**
     * 获取该学生前n个星期的课程信息
     */
    @Override
    public WisdomResult getCourseFrontOfXWeek(TbStudent tbStudent, Integer n) {
        //获取该天前n个星期的起始值
        Map<String, DateTime> map = DateUtils.subXWeekDay(DateUtils.getCurrentTime(), n);
        //返回结果集
        return getCourseOfSpellTime(tbStudent,map);
    }

}
