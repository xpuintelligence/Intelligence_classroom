package com.smart.service;

import com.smart.pojo.WisdomResult;

public interface TeacherAttendanceService {
    /**
     * 查询一段时间内缺勤的人
     */

    /**
     * 查询本节课多少个人缺勤
     */

    /**
     * 查询一段时间内每节课的缺勤情况
     */

    /**
     * 查询本节课有多少人睡觉
     */

    /**
     * 查询一段时间内有有多少人睡觉
     */

    /**
     * 查询一段时间内每一节课多少人睡觉
     */


    /**
     * 查询整个学期该学生在具体某节课上的表现情况
     */
    public WisdomResult getStudentAttOfThisSemester(String courseItemId,String studentId);
    /**
     * 查看某一个学生的具体某一节课的信息
     */
    public WisdomResult getStudentAttOfOneCourse(String courseId,String studentId);

    /**
     * 查看某一个学生在这个学期中某一段时间的具体考勤
     */

    /**
     * 老师修改这节课如何考勤
     */

    /**
     * 查看今天上课的班级的考勤情况
     */
    public WisdomResult getCourseAttOfClassToday(String classId,String courseId);
}
