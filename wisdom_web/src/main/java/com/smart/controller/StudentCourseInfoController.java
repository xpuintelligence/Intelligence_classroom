package com.smart.controller;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.TbStudent;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentCourseService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 查询学生课程信息的controller
 */
@Controller
@RequestMapping("/studentCourseInfo")
@CrossOrigin(origins = {"*", "null"})
public class StudentCourseInfoController {
    @Autowired
    private StudentCourseService studentCourseService;

    /**
     * 获取一段时间内的课程
     * @param begin 开始时间
     * @param end 结束时间
     * @return 含有信息的结果集
     */
    @RequestMapping("/getASpellTime")
    @ResponseBody
    public WisdomResult getCourseListSpellTime(DateTime begin ,DateTime end, ModelMap modelMap, HttpServletRequest request){
        //先获取学生的信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用service函数
        WisdomResult courseOfSpellTime = studentCourseService.getCourseOfSpellTime(student, DateUtils.dateTimeToString(begin), DateUtils.dateTimeToString(end));
        return courseOfSpellTime;
    }

    /**
     * 获取本周的课程
     */
    @RequestMapping("/thisWeek")
    @ResponseBody
    public WisdomResult getCourseListThisWeek(HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        System.out.println(student);
        //调用service方法，获取返回值
        WisdomResult result = studentCourseService.getCourseThisWeek(student);
        return result;
    }

    /**
     * 获取该月的课程信息
     */
    @RequestMapping("/thisMonth")
    @ResponseBody
    public WisdomResult getCourseListThisMonth(HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法，获取返回值
        WisdomResult result = studentCourseService.getCourseThisMonth(student);
        return result;
    }

    /**
     * 获取前x星期的课程信息
     */
    @RequestMapping("/frontXWeek")
    @ResponseBody
    public WisdomResult getCourseListFrontXWeek(Integer n , HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法，获取返回值
        WisdomResult result = studentCourseService.getCourseFrontOfXWeek(student, n);
        return result;
    }

    /**
     * 获取前x月的课程信息
     */
    @RequestMapping("/frontXMonth")
    @ResponseBody
    public WisdomResult getCourseListFrontXMonth(Integer n , HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法，获取返回值
        WisdomResult result = studentCourseService.getCourseFrontOfXMonth(student, n);
        return result;
    }

    /**
     * 第x月的考勤
     */
    @RequestMapping("/xMonth")
    @ResponseBody
    public WisdomResult getCourseListXMonth(Integer n , HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法，获取返回值
        WisdomResult result = studentCourseService.getCourseInXMonth(student, n);
        return result;
    }

    /**
     * 第x周的考勤
     */
    @RequestMapping("xWeek")
    @ResponseBody
    public WisdomResult getCourseListXWeek(Integer n , HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法 获取返回值
        WisdomResult result = studentCourseService.getCourseInXWeek(student, n);
        return result;
    }

    /**
     * 获取今天的课程信息
     */
    @RequestMapping("/today")
    @ResponseBody
    public WisdomResult getCourseList(HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法，获取返回值
        WisdomResult result = studentCourseService.getCourseOfToday(student);
        return result;
    }

    /**
     * 获取这个学期的课程
     */
    @RequestMapping("/thisSemester")
    @ResponseBody
    public WisdomResult getCourseListSemester(HttpServletRequest request){
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法，获取返回值
        WisdomResult result = studentCourseService.getCourseOfThisSemester(student);
        return result;
    }

    /**
     * 获取某一天的课程
     */
    @RequestMapping("/oneday")
    @ResponseBody
    public WisdomResult getCourseListOneday(HttpServletRequest request ,@RequestParam("dateTime") DateTime datetime){
        //转换一下时间的格式
        //先获取学生信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //调用方法，获取返回值
        WisdomResult result = studentCourseService.getCourseOfDay(student, DateUtils.dateTimeToString(datetime));
        return result;
    }



}
