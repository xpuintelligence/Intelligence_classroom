package com.smart.pojo;

import lombok.Data;

/**
 * 每天的考勤汇总
 * @author LM_Code
 * @create 2019-05-13-21:16
 */
@Data
public class DaysAttendanceCollect {
    String date;//日期
    String sum;//总课程数
    String avgGoal;//平均考勤分
}
