package com.smart.controller;

import com.smart.pojo.TbStudent;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentCourseService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 查询学生课程信息的controller
 */
@Controller
@RequestMapping("/course")
@CrossOrigin(origins = "*", maxAge = 3600)

public class StudentCourseInfoController {
    @Autowired
    private StudentCourseService studentCourseService;

    @RequestMapping("/getCourseList")
    public WisdomResult getCourseList(DateTime date, ModelMap modelMap, HttpServletRequest request){
        //先获取学生的信息
        TbStudent student = (TbStudent) request.getSession().getAttribute("tbStudent");
        //调用service函数
        studentCourseService.getCourseOfToday(student);
        return new WisdomResult();
    }



}
