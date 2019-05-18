package com.smart.service;

import com.smart.pojo.TeacherInfo;
import com.smart.pojo.WisdomResult;

public interface TeacherCourseService {
    /**
     * 获取该老师有哪些课程
     */
    public WisdomResult getCourseNumOfTeacher(TeacherInfo teacherInfo);

    /**
     * 查询学生的个人信息
     */
    public WisdomResult getStudentInfo(String studentId);
}
