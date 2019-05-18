package com.smart.controller;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.TbAnswerQuestion;
import com.smart.pojo.TeacherInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.CourseCriticismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 课程答疑
 * 留言
 */

@RequestMapping("/courseCriticism")
public class CourseCriticismController {

    @Autowired
    private CourseCriticismService courseCriticismService;

    /**
     * 添加评论在课程中
     */
    public WisdomResult addCriticismToCourse(TbAnswerQuestion tbAnswerQuestion,HttpServletRequest request){
        //先判断是老师还是学生
        Integer status = (Integer) request.getSession().getAttribute("status");
        StudentInfo studentInfo ;
        TeacherInfo teacherInfo;
        String id = null;
        String fromName = null;
        if (status == 1) {
            studentInfo = (StudentInfo) request.getSession().getAttribute("student");
            id = studentInfo.getId();
            fromName = studentInfo.getName();
        } else if (status == 2){
            teacherInfo = (TeacherInfo) request.getSession().getAttribute("teacher");
            id = teacherInfo.getId();
        }
        tbAnswerQuestion.setFromId(id);
        courseCriticismService.addCriticism(tbAnswerQuestion);
        //获取学生或老师的信息
        return new WisdomResult();
    }

    public WisdomResult getCriticismListForOneCourse(HttpServletRequest request,String courseId){
        return WisdomResult.ok();
    }
}
