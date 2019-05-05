package com.smart.service.impl;

import com.smart.pojo.TbStudent;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentCourseService;

public class StudentCourseServiceImpl implements StudentCourseService {

    /**
     * 获取一段区间内的课程
     * @param tbStudent 要查询的学生
     * @param begin 开始时间
     * @param end 结束时间
     * @return
     */
    @Override
    public WisdomResult getCourseOfSpellTime(TbStudent tbStudent, String begin, String end) {
        return null;
    }

    /**
     * 某一天的课程
     * @param tbStudent 需要查询的学生
     * @param day 某一天的时间
     * @return
     */
    @Override
    public WisdomResult getCourseOfDay(TbStudent tbStudent, String day) {
        return null;
    }

    /**
     * 今天的课程
     * @param tbStudent
     * @return
     */
    @Override
    public WisdomResult getCourseOfToday(TbStudent tbStudent) {
        return null;
    }

    /**
     * 这个学期的课程
     * @param tbStudent
     * @return
     */
    @Override
    public WisdomResult getCourseOfThisSemester(TbStudent tbStudent) {
        return null;
    }

    /**
     * 第n周的课程
     * @param tbStudent
     * @param n
     * @return
     */
    @Override
    public WisdomResult getCourseInXWeek(TbStudent tbStudent, int n) {
        return null;
    }

    @Override
    public WisdomResult getCourseThisWeek(TbStudent tbStudent) {
        return null;
    }
}
