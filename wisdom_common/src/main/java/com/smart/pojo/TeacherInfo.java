package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LM_Code
 * @create 2019-05-10-19:16
 */
@Data
public class TeacherInfo implements Serializable {
    String id;//教师id
    String name;//教师姓名
    String sex;//性别
    String college_id;//学院id
    String college_name;//学院姓名
    String picture;//头像图片
    String wexin_id;//微信号
    String phone_code;//电话号
    String email;//邮箱
    String position_id;//职位id
    String position_name;//职位名
}
