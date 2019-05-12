package com.smart.service;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.WisdomResult;

/**
 * 学生考勤接口
 */
public interface StudentAttendanceService {
    /**
     * 通过起始时间获取某一位学生的某一节课具体一段时间的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param classId 查询的学生信息
     * @param courseId 某一节课的id
     * @return 结果集
     */
    public WisdomResult getSpellTimeAttendance(String start , String end , String classId , String courseId);

    /**
     * 通过起始时间与课程id查询这段时间中某位学生的每一节课的考勤情况
     * @param start 开始时间
     * @param end 结束时间
     * @param studentInfo 学生信息
     * @param courseId 课程id
     */
    public WisdomResult getSpellTimeAttendanceOfCourse(String start , String end , StudentInfo studentInfo,String courseId);


}
