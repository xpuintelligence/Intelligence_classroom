package com.smart.utils;

import com.smart.pojo.WisdomResult;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 专门用来计算时间的时间类
 */
public class DateUtils implements Serializable {

    //定义时间格式
    private String DATE_FORMAT_TOSTRING = "yyyy-MM-dd HH:mm:ss";
    /**
     * 获取当前时间
     */
    public static DateTime getCurrentTime(){
        return new DateTime();
    }

    /**
     *  指定日期前x月，返回该月的月初,与月末的值
     */
    public static Map<String,DateTime> subXMonthDay(DateTime dateTime , int x){
        Map<String,DateTime> map = new HashMap<>();
        //向前推x个月
        DateTime month = dateTime.minusMonths(x);
        //计算该月的第一天
        map.put("start",month.dayOfMonth().withMinimumValue());
        //计算该月的最后一天
        map.put("end",month.dayOfMonth().withMaximumValue());
        //返回
        return map;
    }

    /**
     * 指定日期的前x个星期，返回该星期的星期数
     */
    public static Map<String,DateTime> subXWeekDay(DateTime dateTime , int x){
        Map<String,DateTime> map = new HashMap<>();
        //向前x个星期
        DateTime week = dateTime.minusWeeks(x);
        //计算月初
        map.put("start",week.dayOfWeek().withMinimumValue());
        //计算月末
        map.put("end",week.dayOfWeek().withMaximumValue());
        return map;
    }

    /**
     * 指定日期向后推x个月，获得该月第一天与最后一天
     */
    public static Map<String,DateTime> addXMonthDay(DateTime dateTime , int x){
        Map<String,DateTime> map = new HashMap<>();
        //向前推x个月
        DateTime month = dateTime.plusMonths(x);
        //计算该月的第一天
        map.put("start",month.dayOfMonth().withMinimumValue());
        //计算该月的最后一天
        map.put("end",month.dayOfMonth().withMaximumValue());
        //返回
        return map;
    }

    /**
     * 向后推x个星期，获取该星期第一天与最后一天
     */
    public static Map<String,DateTime> addXWeekDay(DateTime dateTime , int x){
        Map<String,DateTime> map = new HashMap<>();
        //向前x个星期
        DateTime week = dateTime.plusWeeks(x);
        //计算月初
        map.put("start",week.dayOfWeek().withMinimumValue());
        //计算月末
        map.put("end",week.dayOfWeek().withMaximumValue());
        return map;
    }

    /**
     * 传来一个时间，将该时间转换为00：00 ---23：59
     */
    public static Map<String,DateTime> spellTime(DateTime dateTime){
        return spellTime(dateTime,dateTime);
    }

    /**
     * 传来一个时间段，将该时间段转化为起始时间00：00----末尾时间+1天的00：00
     */
    public static Map<String,DateTime> spellTime(DateTime begin,DateTime end) {
        Map<String, DateTime> map = new HashMap<>();
        //0点
        DateTime beginDay = new DateTime(begin.getYear(), begin.getMonthOfYear(), begin.getDayOfMonth()
                , 0, 0);
        //第二天0点
        DateTime tomorrow = end.plusDays(1);
        DateTime tomorrowDay = new DateTime(tomorrow.getYear(), tomorrow.getMonthOfYear(), tomorrow.getDayOfMonth()
                , 0, 0);
        //添加进map中
        map.put("start", beginDay);
        map.put("end", tomorrowDay);
        return map;
    }

    /**
     * 判断传来的时间是否在另外一个时间
     */
    public static WisdomResult isAheadOfTime(DateTime dateTime , DateTime standard){
        WisdomResult wisdomResult = new WisdomResult(1,"true",null);
        if (dateTime.isAfter(standard)){
            wisdomResult.setStatus(0);
            wisdomResult.setMsg("false");
        }
        return wisdomResult;
    }

    /**
     * 传来一个起始时间，判断结束时间是否在开始时间与开学时间之前
     * @param OpenTime 开学时间
     * @param begin 起始时间
     * @param end 结束时间
     * @return 返回判断结果与消息
     */
    public static WisdomResult isAheadOfSpellTime(DateTime OpenTime , DateTime begin , DateTime end){
        //判断开学时间与起始时间的关系
        WisdomResult wisdomResult1 = isAheadOfTime(begin,OpenTime);
        if (wisdomResult1.getStatus() == 0){
            return new WisdomResult(0,"请注意开学时间不可在查询起始时间之后！请修改",null);
        }
        //判断开学时间与结束时间的关系
        WisdomResult wisdomResult2 = isAheadOfTime(end,OpenTime);
        if (wisdomResult2.getStatus() == 0){
            return new WisdomResult(0,"请注意结束时间不可以在开学时间前",null);
        }
        //判断起始时间与结束时间的关系
        WisdomResult wisdomResult3 = isAheadOfTime(end,begin);
        if (wisdomResult3.getStatus() == 0){
            return new WisdomResult(0,"请注意结束时间不可在开始时间之前",null);
        }
        //编写返回信息
        return new WisdomResult(1,"true",null);
    }

    /**
     * 将dateTime时间类型转成字符串
     * @param dateTime
     * @return
     */
    public static String dateTimeToString (DateTime dateTime){
        return dateTime.toString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将yyyy-mm-dd 00:00:00专程yyyy-mm-ddT00:00:00
     * @param s 字符串时间
     */
    public static DateTime stringToDatetime(String s){
        int index = s.indexOf(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s.substring(0,index));
        stringBuilder.append("T");
        stringBuilder.append(s.substring(index+1));
        DateTime dateTime = new DateTime(stringBuilder.toString());
        return dateTime;
    }
    @Test
    public void fun1(){
        int x = 1;
        DateTime dateTime = new DateTime(2010,1,1,2,1);
        DateTime dateTime1 = new DateTime(2012,1,3,4,5);
        final Map<String, DateTime> map = spellTime(dateTime,dateTime1);
        System.out.println();
    }



}
