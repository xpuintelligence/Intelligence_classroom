package com.smart.controller;

import com.smart.pojo.TbStudent;
import com.smart.pojo.WisdomResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@RequestMapping("/weixin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentInfoController {

    /**
     * 返回学生信息
     */
    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public WisdomResult getStudentInfo(HttpServletRequest request){
        TbStudent tbStudent = (TbStudent) request.getSession().getAttribute("tbStudent");
        return WisdomResult.ok(tbStudent);
    }
}
