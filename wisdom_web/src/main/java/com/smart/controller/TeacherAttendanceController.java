package com.smart.controller;

import com.smart.pojo.TeacherInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.TeacherAttendanceService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 老师查询考勤
 */
@Controller
@RequestMapping("/teacherAttendance")
public class TeacherAttendanceController {
    @Autowired
    private TeacherAttendanceService teacherAttendanceService;

    /**
     * 查询某一节课某个同学缺勤次数
     */
    public WisdomResult getAbsentOfACourse(String studentId , String courseId){

        WisdomResult result = teacherAttendanceService.getStudentAbsentOfOneCourse(null, null, courseId, studentId);
        return result;
    }

    /**
     * 按照条件查询具体时间的每天上课的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param status 条件
     */
    @RequestMapping("/getAttOfClassAtASpellTime")
    @ResponseBody
    public WisdomResult getAttOfOneClass(HttpServletRequest request,String start,String end,int status){
        //获取老师信息
        TeacherInfo teacherInfo = (TeacherInfo) request.getSession().getAttribute("teacher");
        WisdomResult result = null;
       //判断条件
       if (status == 1){
           //查询今天的
           result = teacherAttendanceService.getAttToday(teacherInfo);
       }else if (status == 2){
           //查询这周的
           result = teacherAttendanceService.getAttThisWeek(teacherInfo);
       }else if (status == 3){
           //查询这个月的
           result = teacherAttendanceService.getAttThisMont(teacherInfo);
       }else if (status == 4){
           //查询这学期的
           result = teacherAttendanceService.getAttThisSemester(teacherInfo);
       }else if (status == 5){
           //查询某一天的
           result = teacherAttendanceService.getAttOfOneDay(start,teacherInfo);
       }else if (status == 6){
           //查询某一区间的
           result = teacherAttendanceService.getAttASpellTime(start,end,teacherInfo);
       }
       return result;
    }

    /**
     * 通过courseItem查询这整个一节课中所有学生的考勤信息
     * @param courseItemId 某一节具体课程的id
     */
    @RequestMapping("/getStudentAttWithCourseItem")
    @ResponseBody
    public WisdomResult getAllStudentAttWithCourseItemId(String courseItemId){
        return teacherAttendanceService.getAttOfClass(courseItemId);
    }

    /**
     * 通过学生id和课程id查出这个学生整个学期的平均考勤统计
     * @param studentId 学生id
     * @param courseId 课程id
     */
    @RequestMapping("/getStudentAttInCourse")
    @ResponseBody
    public WisdomResult getAStudentAttInCourse(String studentId,String courseId){
        WisdomResult result = teacherAttendanceService.getStudentAttOfCourse(studentId, courseId);
        return result;
    }

    /**
     * 查询这个学生这个学期每一节这个课都对应的考勤信息
     * @param studentId 学生id
     * @param courseId 课程id
     */
    @RequestMapping("/getStudentAttInEveryCourse")
    @ResponseBody
    public WisdomResult getStudentAttInEveryCourse(String studentId,String courseId){
        WisdomResult studentAttInEveryCourse = teacherAttendanceService.getStudentAttInEveryCourse(studentId, courseId);
        return studentAttInEveryCourse;
    }

}
