package com.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbCourseitemPpt implements Serializable {
    private String courseitemId;

    private Date uploadTime;

    private Date updateTime;

    private String fileUrl;

    private static final long serialVersionUID = 1L;

    public String getCourseitemId() {
        return courseitemId;
    }

    public void setCourseitemId(String courseitemId) {
        this.courseitemId = courseitemId == null ? null : courseitemId.trim();
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }
}