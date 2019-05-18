package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LM_Code
 * @create 2019-05-18-15:59
 */
@Data
public class AttendanceSituation implements Serializable {
    String student_id;//学生id
    String name;//学生姓名
    String create_date;//考勤日期
    String courseitem_id;//考勤的那节课
}
