package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LM_Code
 * @create 2019-05-08-19:34
 */
@Data
public class StudentInfo implements Serializable {
    String id;
    String name;
    String sex;
    String phone;
    String email;
    String classId;
    String className;
    String professionId;
    String professionName;
    String collegeId;
    String collegeName;
    String weixinId;
}
