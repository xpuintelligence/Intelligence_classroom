package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.AttendanceItem;
import com.smart.pojo.WisdomResult;
import com.smart.service.CourseService;
import com.smart.service.TeacherAttendanceService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {
    //定义时间格式
    @Value("yyyy-MM-dd HH:mm:ss")
    private String DATE_FORMAT_TOSTRING ;

    @Autowired
    private TbStudentMapper tbStudentMapper;

    @Autowired
    private CourseService courseService;

    /**
     * 查询该学生一学期中该课程的考勤情况
     */
    @Override
    public WisdomResult getStudentAttOfThisSemester(String courseId, String studentId) {
        DateTime opentime = courseService.getOpentime();
        DateTime currentTime = DateUtils.getCurrentTime();
        List<AttendanceItem> attendanceItems = tbStudentMapper.selectAttendanceItemByStudentCourseStartEnd(studentId, courseId, opentime.toString(DATE_FORMAT_TOSTRING),
                currentTime.toString(DATE_FORMAT_TOSTRING));
        return WisdomResult.ok(attendanceItems);
    }

    /**
     * 通过课程id与学生id查询这节课他的考勤情况
     * @param courseItemId 具体某一节课的id
     * @param studentId 学生id
     */
    @Override
    public WisdomResult getStudentAttOfOneCourse(String courseItemId , String studentId) {
        return null;
    }

    /**
     *
     * @param classId
     * @param courseId
     * @return
     */
    @Override
    public WisdomResult getCourseAttOfClassToday(String classId, String courseId) {
        return null;
    }
}
