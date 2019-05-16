package edu.xpu.tim.myfaceapplication.entity;


import lombok.Data;

@Data
public class AttendanceItem {
    String attendance_id;
    String courseitem_id;
    String course_id;
    String name;//课程名
    String time;
    String status;
    String head_up_rate;
    String goal;
    String teacher_id;
    String teacher_name;
    String classroom_id;
    String course_note;
    String student_id;
    String student_name;
}