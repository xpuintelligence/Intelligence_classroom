package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 某同学某个课程的平均考勤情况
 * @author LM_Code
 * @create 2019-05-19-15:35
 */
@Data
public class CourseAVGAttendance implements Serializable {
    String student_id;//学生id
    String name;//学生姓名
    String course_id;//课程id
    String course_name;//课程名称
    String total;//课程上的次数
    String leave_num;//请假次数
    String late_num;//迟到次数
    String absent_num;//缺勤次数
    String attend_num;//正常到勤次数
    String sleep_num;//睡觉次数
    String avg_head_up_rate;//平均抬头率
    String avg_headup_score;//平均抬头分数
    String avg_goal;//平均总分
}
