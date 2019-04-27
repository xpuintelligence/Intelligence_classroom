package com.smart.pojo;

import java.io.Serializable;

public class TbProfessionitem implements Serializable {
    private String professionId;

    private String deanId;

    private static final long serialVersionUID = 1L;

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId == null ? null : professionId.trim();
    }

    public String getDeanId() {
        return deanId;
    }

    public void setDeanId(String deanId) {
        this.deanId = deanId == null ? null : deanId.trim();
    }
}