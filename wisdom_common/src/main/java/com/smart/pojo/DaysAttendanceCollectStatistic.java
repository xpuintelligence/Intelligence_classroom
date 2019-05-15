package com.smart.pojo;

import lombok.Data;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 存放每一天的考勤信息+这里面每一天的考勤信息的平均值
 */
@Data
public class DaysAttendanceCollectStatistic {
    String startTime;//开始时间
    String endTime;//结束时间
    int num;//天数
    double headUpRateAverage;//抬头率平均值
    double AttendanceGoalAverage;//平均考勤得分
    List<DaysAttendanceCollect> list;//这里面存放的是每一天的考勤信息

    public DaysAttendanceCollectStatistic(int num,List<DaysAttendanceCollect> list) {
        this.list = list;
        this.num = num;
        //在这里计算出平均值
        double headUpTotal = 0;
        double AttendanceGoalTotal = 0;
        for (DaysAttendanceCollect daysAttendanceCollect : list) {
            AttendanceGoalTotal += Double.parseDouble(daysAttendanceCollect.avgGoal);
        }
        //计算出考勤的平均值
        this.AttendanceGoalAverage = Double.parseDouble(new DecimalFormat("0.00").format(AttendanceGoalTotal/num));
    }
}
