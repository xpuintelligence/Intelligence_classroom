package com.smart.service.impl;

import com.smart.service.CourseService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CourseServiceImpl implements CourseService, Serializable {
    private DateTime opentime;//开学时间
    private DateTime recessTime;//放假时间
    public CourseServiceImpl() {
        opentime = new DateTime(2019,2,25,0,0);
        recessTime = opentime.plusWeeks(20).minusDays(3);
    }

    /**
     * 开学时间
     * @return
     */
    @Override
    public DateTime getOpentime() {
        return opentime;
    }

    /**
     * 放假时间
     * @return
     */
    @Override
    public DateTime getRecessTime() {
        return getOpentime().plusWeeks(20);
    }

    /**
     * 获取某一天在该学期第几周
     */
    @Override
    public Integer dayOfWeekInThisSemester(DateTime dateTime) {
        //获取开学时间
        DateTime opentime = getOpentime();
        //获取开学时间为今年的第几周
        Period p2 = new Period(opentime, dateTime, PeriodType.yearWeekDay());
        return p2.getWeeks()+1;
    }

    /**
     * 获取该学期的第n周的区间
     * @return map集合
     */
    @Override
    public Map<String, DateTime> getNWeekInThisSemester(int n) {
        //获取该学时间
        DateTime opentime = getOpentime();
        //在开学日期上加n周
        DateTime targetWeek = opentime.plusWeeks(n-1);
        //获取该周周一与周末
        DateTime targetWeekBegin = targetWeek.dayOfWeek().withMinimumValue();
        DateTime targetWeekEnd = targetWeek.dayOfWeek().withMaximumValue();
        //放进map
        Map<String,DateTime> map = new HashMap<>();
        map.put("begin",targetWeekBegin);
        map.put("end",targetWeekEnd);
        //返回
        return map;
    }

    /**
     * 某天为该学期的第几个月
     */
    @Override
    public Integer dayOfMonthInThisSemester(DateTime dateTime) {
        //获取开学时间
        DateTime opentime = getOpentime();
        //获取开学时间为今年的第几个月
        int i = dateTime.getMonthOfYear() - opentime.getMonthOfYear();
        return i+1;
    }

    /**
     * 获取该学期的第n个月的区间
     */
    @Override
    public Map<String, DateTime> getNMonthInThisSemester(int n) {
        Map<String,DateTime> map = new HashMap<>();
        DateTime begin;
        DateTime end;
       //如果是第一个月
       if (n == 1){
           //开始时间
           begin = opentime;
           //结束时间
           end = opentime.dayOfMonth().withMaximumValue();
       }else {
           DateTime targetTime = opentime.plusMonths(n - 1);
           //月首
           begin = targetTime.dayOfMonth().withMinimumValue();
           //月末
           end = targetTime.dayOfMonth().withMaximumValue();
           //判断月末是否在学期外
           if (end.isAfter(recessTime)) {
               end = recessTime;
           }
       }
        //存入map
        map.put("begin",begin);
        map.put("end",end);
        return map;
    }

    /**
     * 该学期有几个月
     * @return
     */
    @Override
    public Integer monthInThisSemester() {
        int i = recessTime.getMonthOfYear() - opentime.getMonthOfYear();
        return i + 1;
    }


    @Test
    public void fun1(){
        DateTime dateTime = new DateTime();
        getNWeekInThisSemester(10);
        Integer integer = dayOfMonthInThisSemester(dateTime);
        System.out.println(integer);
        getNMonthInThisSemester(6);
        System.out.println("该学期有"+monthInThisSemester()+"个月");
    }
}
