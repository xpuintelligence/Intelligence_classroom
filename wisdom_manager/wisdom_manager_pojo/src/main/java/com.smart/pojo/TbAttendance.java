package com.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbAttendance implements Serializable {
    private String attendanceId;

    private String courseitemId;

    private String studentId;

    private Date createDate;

    private String status;

    private Double headUpRate;

    private Integer goal;

    private static final long serialVersionUID = 1L;

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId == null ? null : attendanceId.trim();
    }

    public String getCourseitemId() {
        return courseitemId;
    }

    public void setCourseitemId(String courseitemId) {
        this.courseitemId = courseitemId == null ? null : courseitemId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Double getHeadUpRate() {
        return headUpRate;
    }

    public void setHeadUpRate(Double headUpRate) {
        this.headUpRate = headUpRate;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }
}