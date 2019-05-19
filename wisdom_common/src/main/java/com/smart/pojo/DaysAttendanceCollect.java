package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 每天的考勤汇总
 * @author LM_Code
 * @create 2019-05-13-21:16
 */
@Data
public class DaysAttendanceCollect implements Serializable {
    Date date;//日期
    String sum;//总课程数
    String avg_head_up_rate;//平均抬头率
    String avg_head_up_score;//平均抬头分数
    String avgGoal;//平均考勤分
}
