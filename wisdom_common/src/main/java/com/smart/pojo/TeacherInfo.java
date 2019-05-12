package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LM_Code
 * @create 2019-05-10-19:16
 */
@Data
public class TeacherInfo implements Serializable {
    String id;
    String name;
    String sex;
    String college_id;
    String college_name;
    String picture;
    String wexin_id;
    String phone_code;
    String email;
    String position_id;
    String position_name;
}
