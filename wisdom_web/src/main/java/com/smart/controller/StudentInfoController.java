package com.smart.controller;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.TbStudent;
import com.smart.pojo.WisdomResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/weixin")
public class StudentInfoController {

    /**
     * 返回学生信息
     */
    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public WisdomResult getStudentInfo(HttpServletRequest request){
        StudentInfo studentInfo = (StudentInfo) request.getSession().getAttribute("student");
        return WisdomResult.ok(studentInfo);
    }
}
