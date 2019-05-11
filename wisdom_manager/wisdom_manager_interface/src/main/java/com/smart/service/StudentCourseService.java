package com.smart.service;

import com.smart.pojo.StudentInfo;

import com.smart.pojo.WisdomResult;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * 用于查询课程信息的service
 */
public interface StudentCourseService {

    /**
     *  获取一位同学某段时间内有哪一些课程
     * @param studentInfo 要查询的学生
     * @param begin 开始时间
     * @param end 结束时间
     * @return 返回查询结果R
     */
    public WisdomResult getCourseOfSpellTime(StudentInfo studentInfo , DateTime begin , DateTime end);

    /**
     *  因为joda-time不能进行序列化，所以只能进行字符串型的时间传输
     * @param begin 开始时间的字符串
     * @param end 结束时间的字符串
     */
    public WisdomResult getCourseOfSpellTime(StudentInfo studentInfo , String begin , String end);

    /**
     *  获取一位同学某段时间内有哪一些课程
     * @param studentInfo 要查询的学生
     * @return 返回查询结果
     */
    public WisdomResult getCourseOfSpellTime(StudentInfo studentInfo , Map<String,DateTime> map);

    /**
     *  获取某一天有哪些课程
     * @param studentInfo 需要查询的学生
     * @param day 某一天的时间
     * @return 查询结果
     */
    public WisdomResult getCourseOfDay(StudentInfo studentInfo , DateTime day);

    /**
     * 因为joda-time不支持序列化，所以时间参数不可以之间传递，要到这边了以后才能传递
     * @param day 字符串形式的时间
     */
    public WisdomResult getCourseOfDay(StudentInfo studentInfo , String day);

    /**
     * 获取今天有哪些课程
     */
    public  WisdomResult getCourseOfToday(StudentInfo studentInfo);

    /**
     * 获取这个学期的课程，从开学到学期末
     */
    public WisdomResult getCourseOfThisSemester(StudentInfo studentInfo);

    /**
     * 获取第n周的课程,从开学第一个星期算起
     */
    public WisdomResult getCourseInXWeek(StudentInfo studentInfo , Integer n);

    /**
     * 获取本周有哪些课程
     */
    public WisdomResult getCourseThisWeek(StudentInfo studentInfo);

    /**
     * 获得该学的课程
     */
    public WisdomResult getCourseThisMonth(StudentInfo studentInfo);

    /**
     * 获取该学期第x个月的课程
     */
    public WisdomResult getCourseInXMonth(StudentInfo studentInfo , Integer n);

    /**
     *  获取该学生前n个星期的课程信息
     */
    public WisdomResult getCourseFrontOfXMonth(StudentInfo studentInfo , Integer n);

    /**
     * 获取该学生前n个月的课程信息
     * @param studentInfo
     * @param n
     * @return
     */
    public WisdomResult getCourseFrontOfXWeek(StudentInfo studentInfo , Integer n);

}
