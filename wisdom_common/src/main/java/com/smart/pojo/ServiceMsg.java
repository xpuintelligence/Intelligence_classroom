package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceMsg implements Serializable {
    String id;
    String status;
    String msg;

    public ServiceMsg(){}


    public ServiceMsg(String id , String status , String msg){
        this.id = id;
        this.status = status;
        this.msg = msg;
    }
}
