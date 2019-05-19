package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.mapper.TbTeacherMapper;
import com.smart.pojo.AttendanceItem;
import com.smart.pojo.AttendanceSituation;
import com.smart.pojo.TbTeacher;
import com.smart.pojo.WisdomResult;
import com.smart.service.CourseService;
import com.smart.service.TeacherAttendanceService;
import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {
    //定义时间格式
    @Value("yyyy-MM-dd HH:mm:ss")
    private String DATE_FORMAT_TOSTRING ;

    @Autowired
    private TbStudentMapper tbStudentMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TbTeacherMapper tbTeacherMapper;
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
     * @param start 开始时间
     * @param end 结束时间
     * @param courseId 课程id
     * @param studentId 学生id
     * @return
     */
    @Override
    public WisdomResult getStudentAbsentOfOneCourse(String start, String end, String courseId, String studentId) {
        DateTime currentTime = DateUtils.getCurrentTime();
        Map<String, DateTime> map = DateUtils.formatSpellTime(currentTime);
        //获取今天的缺勤情况
        List<AttendanceSituation> attendanceSituations = tbTeacherMapper.queryAttendanceSituation("absent",start,end,courseId);
        return WisdomResult.ok(attendanceSituations);
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
