package com.smart.controller;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.WisdomResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取个人信息的controller
 */
@Controller
@RequestMapping("wxGetPersonInfo")
public class WeiXinGeiPersonInfo {

    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public WisdomResult getStudentInfo(HttpServletRequest request){
        //从session中获取学生的个人信息
        StudentInfo student = (StudentInfo) request.getSession().getAttribute("student");
        //返回结果
        return WisdomResult.ok(student);
    }
}
