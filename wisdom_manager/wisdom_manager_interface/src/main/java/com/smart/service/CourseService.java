package com.smart.service;

import org.joda.time.DateTime;

import java.util.Map;

public interface CourseService {

    /**
     * 开学时间
     * @return 返回开学日期
     */
    public DateTime getOpentime();

    /**
     * 放假时间
     */
    public DateTime getRecessTime();

    /**
     * 某一天是在该学其中的第几周
     */
    public Integer dayOfWeekInThisSemester(DateTime dateTime);

    /**
     * 获取该学期的第n周
     */
    public Map<String,DateTime> getNWeekInThisSemester(int n);

    /**
     * 获取某一天是这个学期第几个月
     */
    public Integer dayOfMonthInThisSemester(DateTime dateTime);

    /**
     * 获取第n个月的区间
     */
    public Map<String,DateTime> getNMonthInThisSemester(int n);

    /**
     * 计算该学期有几个月
     */
    public Integer monthInThisSemester();
}
