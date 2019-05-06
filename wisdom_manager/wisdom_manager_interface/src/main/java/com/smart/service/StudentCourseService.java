package com.smart.service;

import com.smart.pojo.TbStudent;
import com.smart.pojo.WisdomResult;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * 用于查询课程信息的service
 */
public interface StudentCourseService {

    /**
     *  获取一位同学某段时间内有哪一些课程
     * @param tbStudent 要查询的学生
     * @param begin 开始时间
     * @param end 结束时间
     * @return 返回查询结果
     */
    public WisdomResult getCourseOfSpellTime(TbStudent tbStudent , DateTime begin , DateTime end);

    /**
     *  获取一位同学某段时间内有哪一些课程
     * @param tbStudent 要查询的学生
     * @return 返回查询结果
     */
    public WisdomResult getCourseOfSpellTime(TbStudent tbStudent , Map<String,DateTime> map);
    /**
     *  获取某一天有哪些课程
     * @param tbStudent 需要查询的学生
     * @param day 某一天的时间
     * @return 查询结果
     */
    public WisdomResult getCourseOfDay(TbStudent tbStudent , DateTime day);

    /**
     * 获取今天有哪些课程
     */
    public  WisdomResult getCourseOfToday(TbStudent tbStudent);

    /**
     * 获取这个学期的课程，从开学到学期末
     */
    public WisdomResult getCourseOfThisSemester(TbStudent tbStudent);

    /**
     * 获取第n周的课程,从开学第一个星期算起
     */
    public WisdomResult getCourseInXWeek(TbStudent tbStudent , Integer n);

    /**
     * 获取本周有哪些课程
     */
    public WisdomResult getCourseThisWeek(TbStudent tbStudent);

    /**
     * 获得该学的课程
     */
    public WisdomResult getCourseThisMonth(TbStudent tbStudent);

    /**
     * 获取该学期第x个月的课程
     */
    public WisdomResult getCourseInXMonth(TbStudent tbStudent , Integer n);

    /**
     *  获取该学生前n个星期的课程信息
     */
    public WisdomResult getCourseFrontOfXMonth(TbStudent tbStudent , Integer n);

    /**
     * 获取该学生前n个月的课程信息
     * @param tbStudent
     * @param n
     * @return
     */
    public WisdomResult getCourseFrontOfXWeek(TbStudent tbStudent , Integer n);

}
