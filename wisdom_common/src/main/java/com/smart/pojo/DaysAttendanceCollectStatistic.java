package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 存放每一天的考勤信息+这里面每一天的考勤信息的平均值
 */
@Data
public class DaysAttendanceCollectStatistic implements Serializable {
    String startTime;//开始时间
    String endTime;//结束时间
    int num;//天数
    String headUpRateAverage;//抬头率平均值
    String AttendanceGoalAverage;//平均考勤得分
    List<DaysAttendanceCollect> list;//这里面存放的是每一天的考勤信息

    /**
     * 无参构造
     */
    public DaysAttendanceCollectStatistic(){

    }

    /**
     * 有参构造
     * @param num 中间间隔多少天
     * @param list 每一天的大致考勤
     */
    public DaysAttendanceCollectStatistic(int num,List<DaysAttendanceCollect> list) {
        this.list = list;
        this.num = num+1;
        //在这里计算出平均值
        double headUpTotal = 0;
        double AttendanceGoalTotal = 0;
        for (DaysAttendanceCollect daysAttendanceCollect : list) {
            AttendanceGoalTotal += Double.parseDouble(daysAttendanceCollect.avgGoal);
            headUpTotal += Double.parseDouble(daysAttendanceCollect.avg_head_up_rate);
        }
        //计算出考勤的平均值
        this.AttendanceGoalAverage = new DecimalFormat("0.0").format(AttendanceGoalTotal/num);
        this.headUpRateAverage = new DecimalFormat("0.0").format(headUpTotal/num);
    }
}
