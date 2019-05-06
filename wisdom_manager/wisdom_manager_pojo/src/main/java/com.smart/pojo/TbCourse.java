package com.smart.pojo;

import java.io.Serializable;

public class TbCourse implements Serializable {
    private String id;

    private String name;

    private Integer year;

    private String semeter;

    private String courseNote;

    private Integer attendanceTotalScore;

    private Integer leaveScore;

    private Integer attendScore;

    private Integer lateAttendScore;

    private Integer headUpScore;

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