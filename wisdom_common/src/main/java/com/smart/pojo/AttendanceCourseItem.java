package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 这节课的考勤状况
 * @author LM_Code
 * @create 2019-05-19-15:03
 */
@Data
public class AttendanceCourseItem implements Serializable {
    String attendance_id;//考勤id
    String courseitem_id;//这节课的id
    String student_id;//学生id
    String student_name;//学生姓名
    String create_date;//上课时间
    String status;//到勤状态
    String head_up_rate;//抬头率
    String headup_score;//抬头分数
    String goal;//总分
    String sleep;//睡觉状态
}
