package com.smart.controller;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentAttendanceService;
import com.smart.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
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
    public WisdomResult getSoellTimeAttOfStudentCourse(String start, String end,
                                                       String courseId,HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo)request.getSession().getAttribute("student");
        //调用service方法
        WisdomResult result = studentAttendanceService.getSpellTimeAttendanceOfCourse(start, end, studentInfo, courseId);
        return result;
    }




}
