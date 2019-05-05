package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.TbStudent;
import com.smart.pojo.WCourseInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentCourseService;
import com.smart.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class StudentCourseServiceImpl implements StudentCourseService, Serializable {
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
        //获取当前时间
        DateTime currentTime = DateUtils.getCurrentTime();
        //获取时间段
        Map<String, DateTime> map = DateUtils.spellTime(currentTime);
        //获取课程信息列表
        List<WCourseInfo> wCourseInfoList = tbStudentMapper.selectWCourseInfo(tbStudent.getClassId(),
                map.get("current").toString(DATE_FORMAT_TOSTRING), map.get("tomorrow").toString(DATE_FORMAT_TOSTRING));
        //定义返回值
        WisdomResult wisdomResult ;
        //判断返回值是否为空
        if (wCourseInfoList.size() == 0){
            wisdomResult = new WisdomResult(1,"该时间段中无课程信息",wCourseInfoList);
        }else {
            wisdomResult = new WisdomResult(2,"true",wCourseInfoList);
        }
        return wisdomResult;
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

    /**
     *
     * @param tbStudent
     * @return
     */
    @Override
    public WisdomResult getCourseThisWeek(TbStudent tbStudent) {
        return null;
    }

}
