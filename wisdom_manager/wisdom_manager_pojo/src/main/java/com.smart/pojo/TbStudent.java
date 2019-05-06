package com.smart.pojo;

import java.io.Serializable;

public class TbStudent implements Serializable {
    private String id;

    private String name;

    private String sex;

    private String picture;

    private String classId;

    private String professionId;

    private String collegeId;

    private String password;

    private String wexinId;

    private String phoneCode;

    private String email;

    private String identityId;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId == null ? null : professionId.trim();
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId == null ? null : collegeId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getWexinId() {
        return wexinId;
    }

    public void setWexinId(String wexinId) {
        this.wexinId = wexinId == null ? null : wexinId.trim();
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode == null ? null : phoneCode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId == null ? null : identityId.trim();

    }

    @Override
    public String toString() {
        return "TbStudent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", picture='" + picture + '\'' +
                ", classId='" + classId + '\'' +
                ", professionId='" + professionId + '\'' +
                ", collegeId='" + collegeId + '\'' +
                ", password='" + password + '\'' +
                ", wexinId='" + wexinId + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                ", email='" + email + '\'' +
                ", identityId='" + identityId + '\'' +
                '}';
    }
}