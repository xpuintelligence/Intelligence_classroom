package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接受登陆参数的类
 */
@Data
public class RequestLogin implements Serializable {
    private String account; //账号
    private String password; //密码
    private int status; //1为学生，2为老师
}
