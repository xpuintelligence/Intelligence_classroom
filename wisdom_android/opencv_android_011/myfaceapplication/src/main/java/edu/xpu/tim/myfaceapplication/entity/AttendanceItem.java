package edu.xpu.tim.myfaceapplication.entity;

import java.util.Date;

import lombok.Data;

@Data
public class AttendanceItem {
    String courseId;//课程id
    String courseName;//课程名
    Date time;//上课时间
    String courseNote;//课程描述

    Double attendanceTotalScore; //出勤总分
    Double leaveScore;  //缺勤扣分
    Double attendScore; //出勤得分
    Double lateAttendScore; //迟到扣分
    Double headUpScore; //抬头得分

    String classroomId;//教室id
    String teacherId;//学生id
    String teacherName;//学生名
}