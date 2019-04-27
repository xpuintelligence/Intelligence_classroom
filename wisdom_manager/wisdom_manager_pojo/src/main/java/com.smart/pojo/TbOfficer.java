package com.smart.pojo;

import java.io.Serializable;

public class TbOfficer implements Serializable {
    private String id;

    private String name;

    private String wexinId;

    private String phoneCode;

    private String email;

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
}