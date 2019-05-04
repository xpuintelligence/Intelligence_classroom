package com.smart.pojo;

import java.io.Serializable;

public class TbCourse implements Serializable {
    private String id;//课程id

    private String name;//课程名称

    private Integer year;//课程所在年份

    private String semeter;//课程所在学期

    private String courseNote;//课程详情

    private Integer attendanceTotalScore;//课程考情所在占期末总分比

    private Integer leaveScore;//请假分数

    private Integer attendScore;//考勤分数

    private Integer lateAttendScore;//迟到分数

    private Integer headUpScore;//抬头率所占课堂考勤分数

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSemeter() {
        return semeter;
    }

    public void setSemeter(String semeter) {
        this.semeter = semeter == null ? null : semeter.trim();
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote == null ? null : courseNote.trim();
    }

    public Integer getAttendanceTotalScore() {
        return attendanceTotalScore;
    }

    public void setAttendanceTotalScore(Integer attendanceTotalScore) {
        this.attendanceTotalScore = attendanceTotalScore;
    }

    public Integer getLeaveScore() {
        return leaveScore;
    }

    public void setLeaveScore(Integer leaveScore) {
        this.leaveScore = leaveScore;
    }

    public Integer getAttendScore() {
        return attendScore;
    }

    public void setAttendScore(Integer attendScore) {
        this.attendScore = attendScore;
    }

    public Integer getLateAttendScore() {
        return lateAttendScore;
    }

    public void setLateAttendScore(Integer lateAttendScore) {
        this.lateAttendScore = lateAttendScore;
    }

    public Integer getHeadUpScore() {
        return headUpScore;
    }

    public void setHeadUpScore(Integer headUpScore) {
        this.headUpScore = headUpScore;
    }
}