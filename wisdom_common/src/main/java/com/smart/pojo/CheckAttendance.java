package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 *  查看今天有几节课+平均考勤总分+平均抬头率+缺勤人数+睡觉人数
 * @author LM_Code
 * @create 2019-05-19-10:23
 */
@Data
public class CheckAttendance implements Serializable {
    String courseitem_id;//这节课的id
    int number;//人数
    String avg_goal;//这节课平均分数
    String avg_head_up_rate;//这节课平均抬头率
    String avg_headup_score;//抬头平均分
    int absent_num;//这节课缺勤人数
    int sleep_num;//这节课睡觉人数
    String create_date;//这节课上课时间
}
