package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author LM_Code
 * @create 2019-05-14-19:45
 */
@Data
public class StageAttendanceCollect implements Serializable {
    String avg_head_up_rate;
    String avg_goal;
    String attend_times;
    String late_times;
    String leave_times;
    String absent_times;
    List<AttendanceItem> attendanceItems;
}
