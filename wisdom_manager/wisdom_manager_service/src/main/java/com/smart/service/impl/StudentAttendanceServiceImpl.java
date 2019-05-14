package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.AttendanceItem;
import com.smart.pojo.StudentInfo;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentAttendanceService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    //定义时间格式
    @Value("yyyy-MM-dd HH:mm:ss")
    private String DATE_FORMAT_TOSTRING ;

    @Autowired
    private TbStudentMapper tbStudentMapper;
    /**
     * 通过起始时间获取某一个班的某一节课具体一段时间的考勤信息
     * @param start 开始时间
     * @param end 结束时间
     * @param classId 查询的学生信息
     * @param courseId 某一节课的id
     * @return
     */
    @Override
    public WisdomResult getSpellTimeAttendance(String start, String end, String classId, String courseId) {
        DateTime startTime = DateUtils.stringToDatetime(start);
        DateTime endTime = DateUtils.stringToDatetime(end);
        //获得到这段时间的课程的考勤信息
        List<AttendanceItem> attendanceItems = tbStudentMapper.selectAttendanceItemByClassCourseStartEnd(classId,
                courseId, startTime.toString(DATE_FORMAT_TOSTRING), endTime.toString(DATE_FORMAT_TOSTRING));
        return WisdomResult.ok(attendanceItems);

    }

    /**
     * 通过起始时间与课程id查询这段时间中某位学生的每一节课的考勤情况
     * @param start 开始时间
     * @param end 结束时间
     * @param studentInfo 学生信息
     * @param courseId 课程id
     */
    @Override
    public WisdomResult getSpellTimeAttendanceOfCourse(String start, String end, StudentInfo studentInfo, String courseId) {
        DateTime startTime = DateUtils.stringToDatetime(start);
        DateTime endTime = DateUtils.stringToDatetime(end);
        //获取这段时间的考勤信息
        List<AttendanceItem> attendanceItems = tbStudentMapper.selectAttendanceItemByStudentCourseStartEnd(studentInfo.getId(), courseId, startTime.toString(DATE_FORMAT_TOSTRING),
                endTime.toString(DATE_FORMAT_TOSTRING));
        //返回查询数据
        return WisdomResult.ok(attendanceItems);
    }
}
