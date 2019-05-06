package com.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbCourseitemHomework implements Serializable {
    private String courseitemId;

    private String studentId;

    private Date uploadTime;

    private Date updateTime;

    private String homeworkUrl;

    private static final long serialVersionUID = 1L;

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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHomeworkUrl() {
        return homeworkUrl;
    }

    public void setHomeworkUrl(String homeworkUrl) {
        this.homeworkUrl = homeworkUrl == null ? null : homeworkUrl.trim();
    }
}