package com.smart.pojo;

import com.smart.utils.DateUtils;
import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 对每天的考勤进行分装
 */
@Data
public class CheckAttendanceStatistic implements Serializable {
    List<CheckAttendance> checkAttendanceList;//这一天的考勤list
    String date;//日期
    int headcount;//总人数
    int sleepNum = 0;//睡觉人数
    int absentNum;//缺勤人数
    double sleepRate;//睡觉百分比
    double sbsentRate;//缺勤率
    double ToOftenRate;//到勤率
    double attGoalAve;//考勤平均分
    double headupGoalAue;//抬头率分数
    double headUpAve;//抬头率平均分
    public CheckAttendanceStatistic(){}
    public CheckAttendanceStatistic(List<CheckAttendance> list){
        this.checkAttendanceList = list;
         headcount = 0 ;//总人数
         sleepNum = 0;//睡觉人数
         absentNum = 0;//缺勤人数
         sleepRate = 0;//睡觉百分比
         sbsentRate = 0;//缺勤率
         ToOftenRate = 0;//到勤率
         attGoalAve = 0;//考勤平均分
         headupGoalAue = 0;//抬头率分数
         headUpAve = 0;//抬头率平均分
        //获取今天有几节课
        int num = list.size();
        for (CheckAttendance attendance : list) {
            //计算睡觉的人数
            sleepNum += attendance.getSleep_num();
            //计算一共多少人
            headcount += attendance.getNumber();
            //计算缺勤的人数
            absentNum += attendance.getAbsent_num();
            //先算出总分
            attGoalAve += Double.parseDouble(attendance.getAvg_goal());
            headupGoalAue += Double.parseDouble(attendance.getAvg_headup_score());
            headUpAve += Double.parseDouble(attendance.getAvg_head_up_rate());
            //计算出日期

            DateTime dateTime = DateUtils.stringToDatetime(attendance.create_date);
            this.date = dateTime.toString("yyyy-MM-dd");
        }
        if (num == 0)
            num = 1;
        if(headcount == 0)
            headcount = 1;
        //计算抬头率、抬头率平均分、考勤平均分
        headUpAve /= num;
        headupGoalAue /= num;
        attGoalAve /= num;
        //计算几率
        sleepRate = (double)sleepNum / (double)headcount;
        sbsentRate = (double) absentNum/(double)headcount;
        ToOftenRate = 1-sbsentRate;
        //对double进行格式化
        sleepRate = Double.parseDouble(new DecimalFormat("0.0000").format(sleepRate));
        sbsentRate = Double.parseDouble(new DecimalFormat("0.0000").format(sbsentRate));
        ToOftenRate = Double.parseDouble(new DecimalFormat("0.0000").format(ToOftenRate));
        attGoalAve = Double.parseDouble(new DecimalFormat("0.00").format(attGoalAve));
        headupGoalAue = Double.parseDouble(new DecimalFormat("0.00").format(headupGoalAue));
        headUpAve =  Double.parseDouble(new DecimalFormat("0.0000").format(headUpAve));
    }
}
