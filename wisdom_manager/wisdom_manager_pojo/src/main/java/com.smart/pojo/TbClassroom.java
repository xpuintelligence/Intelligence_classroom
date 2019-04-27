package com.smart.pojo;

import java.io.Serializable;

public class TbClassroom implements Serializable {
    private String id;

    private String segment;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment == null ? null : segment.trim();
    }
}