package com.smart.pojo;


import java.io.Serializable;

/**
 * @author LM_Code
 * @create 2019-05-09-20:45
 */
public class AttendanceItem implements Serializable {
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

    public String getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(String attendance_id) {
        this.attendance_id = attendance_id;
    }

    public String getCourseitem_id() {
        return courseitem_id;
    }

    public void setCourseitem_id(String courseitem_id) {
        this.courseitem_id = courseitem_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHead_up_rate() {
        return head_up_rate;
    }

    public void setHead_up_rate(String head_up_rate) {
        this.head_up_rate = head_up_rate;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(String classroom_id) {
        this.classroom_id = classroom_id;
    }

    public String getCourse_node() {
        return course_node;
    }

    public void setCourse_node(String course_node) {
        this.course_node = course_node;
    }
}
