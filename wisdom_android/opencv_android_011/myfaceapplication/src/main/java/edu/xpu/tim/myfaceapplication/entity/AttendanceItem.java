package edu.xpu.tim.myfaceapplication.entity;

import lombok.Data;

@Data
public class AttendanceItem {
    String attendance_id;//考勤id
    String courseitem_id;//这一节课的id
    String name;//课程名
    String time;//上课时间
    String status;//到勤状态
    String head_up_rate;//抬头率
    String goal;//总分
    String teacher_id;//老师id
    String teacher_name;//老师姓名
    String classroom_id;//教室id
    String course_node;//课程node
    String student_id;//学生id
    String student_name;//学生名
}