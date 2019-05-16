package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LM_Code
 * @create 2019-05-05-15:27
 */
@Data
public class WCourseInfo implements Serializable {
    String id;
    Date time;
    String courseId;
    String courseName;
    String courseNode;
    int attendanceTotalScore;
    int leaveScore;
    int attendScore;
    int lateAttendScore;
    int headUpScore;
    String classroomId;
    String teacherId;
    String teacherName;

}
