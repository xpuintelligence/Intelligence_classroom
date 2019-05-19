package com.smart.controller;

import com.smart.pojo.WisdomResult;
import com.smart.service.TeacherAttendanceService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
