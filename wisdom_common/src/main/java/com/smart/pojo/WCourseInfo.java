package com.smart.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LM_Code
 * @create 2019-05-05-15:27
 */
public class WCourseInfo implements Serializable {
    String id;
    Date time;
    String courseId;
    String courseName;
    String courseNote;
    int attendanceTotalScore;
    int leaveScore;
    int attendScore;
    int lateAttendScore;
    int headUpScore;
    String classroomId;
    String teacherId;
    String teacherName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public int getAttendanceTotalScore() {
        return attendanceTotalScore;
    }

    public void setAttendanceTotalScore(int attendanceTotalScore) {
        this.attendanceTotalScore = attendanceTotalScore;
    }

    public int getLeaveScore() {
        return leaveScore;
    }

    public void setLeaveScore(int leaveScore) {
        this.leaveScore = leaveScore;
    }

    public int getAttendScore() {
        return attendScore;
    }

    public void setAttendScore(int attendScore) {
        this.attendScore = attendScore;
    }

    public int getLateAttendScore() {
        return lateAttendScore;
    }

    public void setLateAttendScore(int lateAttendScore) {
        this.lateAttendScore = lateAttendScore;
    }

    public int getHeadUpScore() {
        return headUpScore;
    }

    public void setHeadUpScore(int headUpScore) {
        this.headUpScore = headUpScore;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "WCourseInfo{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseNote='" + courseNote + '\'' +
                ", attendanceTotalScore=" + attendanceTotalScore +
                ", leaveScore=" + leaveScore +
                ", attendScore=" + attendScore +
                ", lateAttendScore=" + lateAttendScore +
                ", headUpScore=" + headUpScore +
                ", classroomId='" + classroomId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
