package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LM_Code
 * @create 2019-05-08-19:34
 */
@Data
public class StudentInfo implements Serializable {
    String id;//学生id
    String name;//姓名
    String sex;//性别
    String phone;//电话号
    String email;//邮件
    String picture;//头像
    String classId;//班级id
    String className;//班级名
    String professionId;//专业id
    String professionName;//专业名
    String collegeId;//学院id
    String collegeName;//学院名
    String weixinId;//微信号
}
