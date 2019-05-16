package com.smart.pojo;

import lombok.Data;

@Data
public class ServiceMsg {
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
