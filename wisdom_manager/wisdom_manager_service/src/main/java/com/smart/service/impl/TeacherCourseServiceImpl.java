package com.smart.service.impl;

import com.smart.mapper.TbCourseTeacherMapper;
import com.smart.mapper.TbStudentMapper;
import com.smart.mapper.TbTeacherMapper;
import com.smart.pojo.*;
import com.smart.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    //定义时间格式
    @Value("yyyy-MM-dd HH:mm:ss")
    private String DATE_FORMAT_TOSTRING ;

    @Autowired
    private TbTeacherMapper tbTeacherMapper;

    @Autowired
    private TbCourseTeacherMapper tbCourseTeacherMapper;

    @Autowired
    private TbStudentMapper tbStudentMapper;
    /**
     * 查询这个老师有几门课
     */
    @Override
    public WisdomResult getCourseNumOfTeacher(TeacherInfo teacherInfo) {
        TbCourseTeacherExample tbCourseTeacherExample = new TbCourseTeacherExample();
        TbCourseTeacherExample.Criteria criteria = tbCourseTeacherExample.createCriteria();
        criteria.andTeacherIdEqualTo(teacherInfo.getId());
        long l = tbCourseTeacherMapper.countByExample(tbCourseTeacherExample);
        return WisdomResult.ok(l);
    }

    /**
     * 同学学生学号查询学生个人信息
     */
    @Override
    public WisdomResult getStudentInfo(String studentId) {
        StudentInfo studentInfo = tbStudentMapper.selectStudentInfoById(studentId);
        return WisdomResult.ok(studentInfo);
    }



}
