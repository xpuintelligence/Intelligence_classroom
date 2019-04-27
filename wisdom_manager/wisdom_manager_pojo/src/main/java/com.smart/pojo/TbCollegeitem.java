package com.smart.pojo;

import java.io.Serializable;

public class TbCollegeitem implements Serializable {
    private String collegeId;

    private String deanId;

    private static final long serialVersionUID = 1L;

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId == null ? null : collegeId.trim();
    }

    public String getDeanId() {
        return deanId;
    }

    public void setDeanId(String deanId) {
        this.deanId = deanId == null ? null : deanId.trim();
    }
}