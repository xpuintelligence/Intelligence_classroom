package com.smart.controller;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentAttendanceService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用来获取学生考勤信息的controller
 */
@Controller
@RequestMapping("studetnAttendance")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentAttendanceController {
    @Autowired
    private StudentAttendanceService studentAttendanceService;

    /**
     * 获取某一段时间内，某一个班级的考勤
     * @param start 开始时间
     * @param end 结束时间
     * @param courseId 课程id
     */
    @RequestMapping("/oneCourseAtt")
    @ResponseBody
    public WisdomResult getSpellTimeAttendanceOfOneCourse(@RequestParam("start") DateTime start,
                                                          @RequestParam("end") DateTime end,
                                                          String courseId, String classId,
                                                          HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo) request.getSession().getAttribute("student");
        //调用service层方法
        WisdomResult timeAttendacneResult = studentAttendanceService.getSpellTimeAttendance(DateUtils.dateTimeToString(start), DateUtils.dateTimeToString(end),
                classId, courseId);
        return timeAttendacneResult;
    }

    /**
     * 通过传输来的课程id，与起始时间计算出该学生这段时间内该课程的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param courseId 课程id
     */
    @RequestMapping("/getCourseOfStudentWithSpellTime")
    @ResponseBody
    public WisdomResult getSpellTimeAttOfStudentCourse(String start, String end,
                                                       String courseId,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getSpellTimeAttendanceOfOneCourse(start, end, studentInfo, courseId);
        return result;
    }

    /**
     * 查询本周某一节课的考勤信息
     * @param courseId 课程id
     */
        @RequestMapping("/oneCourseAttThisWeek")
    @ResponseBody
    public WisdomResult getThisWeekOneCourseAtt(String courseId,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getThisWeekAttendanceOfOneCourse(studentInfo, courseId);
        return res;
    }

    /**
     * 查询上一周某一节课的考勤信息
     * @param courseId 课程id
     */
    @RequestMapping("/oneCourseAttLastWeek")
    @ResponseBody
    public WisdomResult getLastWeekOneCourseAtt(String courseId,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getLastWeekAttendanceOfOneCourse(studentInfo, courseId);
        return res;
    }

    /**
     * 查询本月某一节课的考勤信息
     * @param courseId 课程id
     */
    @RequestMapping("/oneCourseAttThisMonth")
    @ResponseBody
    public WisdomResult getThisMonthOneCourseAtt(String courseId,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getThisMonthAttendanceOfOneCourse(studentInfo, courseId);
        return res;
    }

    /**
     * 查询上一月某一节课的考勤信息
     * @param courseId 课程id
     */
    @RequestMapping("/oneCourseAttLastMonth")
    @ResponseBody
    public WisdomResult getLastMonthOneCourseAtt(String courseId,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getLastMonthAttendanceOfOneCourse(studentInfo, courseId);
        return res;
    }

    /**
     * 查询这个学期的课程信息
     * @param courseId 课程id
     */
    @RequestMapping("/oneCourseAttThisSemester")
    @ResponseBody
    public WisdomResult getThisSemesterOneCourseAtt(String courseId,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getThisSemesterAttendanceOfOneCourse(studentInfo, courseId);
        return res;
    }

    /**
     * 查询该学生一段时间内的所有课程的考勤信息
     */
    @RequestMapping("/allCourseAttASpellTime")
    @ResponseBody
    public WisdomResult getASpellTimeAllCourseAtt(@RequestParam("start")DateTime start,@RequestParam("end")DateTime end, HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getASpellTimeAttendanceOfAllCoourse(DateUtils.dateTimeToString(start),
                DateUtils.dateTimeToString(end),studentInfo);
        return res;
    }

    /**
     * 查询该学生开学到现在内的所有课程的考勤信息
     */
    @RequestMapping("/allCourseAttThisSemester")
    @ResponseBody
    public WisdomResult getThisSemesterAllCourseAtt(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getThisSemesterAttendanceOfAllCoourse(studentInfo);
        return res;
    }

    /**
     * 查询该学生本周内的所有课程的考勤信息
     */
    @RequestMapping("/allCourseAttThisWeek")
    @ResponseBody
    public WisdomResult getThisWeekAllCourseAtt(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getThisWeekAttendanceOfAllCoourse(studentInfo);
        return res;
    }

    /**
     * 查询该学生上周内的所有课程的考勤信息
     */
    @RequestMapping("/allCourseAttLastWeek")
    @ResponseBody
    public WisdomResult getLastWeekAllCourseAtt(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getLastWeekAttendanceOfAllCoourse(studentInfo);
        return res;
    }

    /**
     * 查询该学生本月内的所有课程的考勤信息
     */
    @RequestMapping("/allCourseAttThisMonth")
    @ResponseBody
    public WisdomResult getThisMonthAllCourseAtt(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getThisMonthAttendanceOfAllCoourse(studentInfo);
        return res;
    }

    /**
     * 查询该学生上月内的所有课程的考勤信息
     */
    @RequestMapping("/allCourseAttLastMonth")
    @ResponseBody
    public WisdomResult getLastMonthAllCourseAtt(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult res = studentAttendanceService.getLastMonthAttendanceOfAllCoourse(studentInfo);
        return res;
    }


    /**
     * *****************************************************************
     * 查询每天的考勤统计
     * 平均得分  几节课  哪一天  平均抬头率
     */

    /**
     * 查询一段时间内的每一天的大概考勤信息以及统计这段时间的东西
     */
    @RequestMapping("/getProAttOfST")
    @ResponseBody
    public WisdomResult getASpellTimeProbableAttOfEveryday(@RequestParam("start")DateTime start,
                                                            @RequestParam("end")DateTime end,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getASpellTimeProbableAttOfEveryday(studentInfo, DateUtils.dateTimeToString(start),
                DateUtils.dateTimeToString(end));
        return result;
    }
    /**
     * 查询本周的大概考勤信息以及统计这段时间的东西
     */
    @RequestMapping("/getProAttOfTW")
    @ResponseBody
    public WisdomResult getThisWeekProbableAttOfEveryday(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getThisWeekProbableAttOfEveryday(studentInfo);
        return result;
    }
    /**
     * 查询上一周的大概考勤信息以及统计这段时间的东西
     */
    @RequestMapping("/getProAttOfLW")
    @ResponseBody
    public WisdomResult getLastWeekProbableAttOfEveryday(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getLastWeekProbableAttOfEveryday(studentInfo);
        return result;
    }
    /**
     * 查询本月的每一天的大概考勤信息以及统计这段时间的东西
     */
    @RequestMapping("/getProAttOfTM")
    @ResponseBody
    public WisdomResult getThisMonthProbableAttOfEveryday(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getThisMonthProbableAttOfEveryday(studentInfo);
        return result;
    }
    /**
     * 查询上个月的每一天的大概考勤信息以及统计这段时间的东西
     */
    @RequestMapping("/getProAttOfLM")
    @ResponseBody
    public WisdomResult getLastMonthProbableAttOfEveryday(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getLastMonthProbableAttOfEveryday(studentInfo);
        return result;
    }
    /**
     * 查询某天的考勤大致情况
     */
    @RequestMapping("/getProAttOfOD")
    @ResponseBody
    public WisdomResult getOneDayProbableAttOfEveryday(@RequestParam("oneday")DateTime oneday, HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getOneDayProbableAttOfEveryday(studentInfo,
                DateUtils.dateTimeToString(oneday));
        return result;
    }
    /**
     * 查询今天的考勤大致情况
     */
    @RequestMapping("/getProAttOfT")
    @ResponseBody
    public WisdomResult getTodayProbableAttOfEveryday(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.gettodayProbableAttOfEveryday(studentInfo);
        return result;
    }
    /**
     * 查询这个学期的考勤大致情况
     */
    @RequestMapping("/getProAttOfTS")
    @ResponseBody
    public WisdomResult getThisSemesterProbableAttOfEveryday(Integer page,Integer size,HttpServletRequest request){
        if (page == null || page == 0  )
            page = 1;
        if (size == null || size == 0)
            size = 15;
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getThisSemesterProbableAttOfEveryday(page,size,studentInfo);
        return result;
    }

    /**
     * 昨天与今天
     */
    @RequestMapping("/yesAndTodayDiff")
    @ResponseBody
    public WisdomResult getYesterdayWithTodayAttDiff(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service
        WisdomResult result = studentAttendanceService.getYesterdayWithTodayAttDiff(studentInfo);
        return result;
    }
    /**
     * 这周与上周
     */
    @RequestMapping("/lastAndThisWeekDiff")
    @ResponseBody
    public WisdomResult getLastWeekWithThisWeekAttDiff(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service
        WisdomResult result = studentAttendanceService.getLastWeekWithThisWeekAttDiff(studentInfo);
        return result;
    }

    /**
     * 这月与上月
     */
    @RequestMapping("/lastAndThisMonthDiff")
    @ResponseBody
    public WisdomResult getLastMonthWithThisMonthAttDiff(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service
        WisdomResult result = studentAttendanceService.getLastMonthWithThisMonthAttDiff(studentInfo);
        return result;
    }


}
