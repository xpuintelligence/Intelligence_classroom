package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.mapper.TbTeacherMapper;
import com.smart.pojo.*;
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
     * @param classId 班级id
     * @param courseId 课程id
     * @return
     */
    @Override
    public WisdomResult getCourseAttOfClassToday(String classId, String courseId) {

        return null;
    }

    /**
     * 查询老师带的一段时间里面的课程
     * @param start 开始时间
     * @param end 结束时时间
     * @param teacherInfo 教师信息
     */
    @Override
    public WisdomResult getAttASpellTime(String start, String end, TeacherInfo teacherInfo) {
        //获取这段时间内的每节课的考勤信息
        List<CheckAttendance> checkAttendances = tbStudentMapper.queryCheckAttendance(start, end, teacherInfo.getId());
        //对这节课进行分装
        return WisdomResult.ok();
    }

    /**
     * 获取今天上课的考勤信息每一节课的考勤信息
     * @param teacherInfo 教师信息
     * @return
     */
    @Override
    public WisdomResult getAttToday(TeacherInfo teacherInfo) {
        return null;
    }

    /**
     * 本周每节课的考勤信息
     * @param teacherInfo
     * @return
     */
    @Override
    public WisdomResult getAttThisWeek(TeacherInfo teacherInfo) {
        return null;
    }

    /**
     * 本月每节课的考勤信息
     * @param teacherInfo
     * @return
     */
    @Override
    public WisdomResult getAttThisMont(TeacherInfo teacherInfo) {
        return null;
    }

    /**
     * 这个学期每节课的考勤信息
     * @param teacherInfo
     * @return
     */
    @Override
    public WisdomResult getAttThisSemester(TeacherInfo teacherInfo) {
        return null;
    }

    /**
     * 某一天的考勤信息
     * @param day 某一天的时间
     * @return
     */
    @Override
    public WisdomResult getAttOfOneDay(String day , TeacherInfo teacherInfo) {
        return null;
    }

    /**
     * 某一节课整个班级的考勤信息
     * @param crouseItemId 某一节课的id
     */
    @Override
    public WisdomResult getAttOfClass(String crouseItemId) {
        return null;
    }

    /**
     * 某一个学生这一学期这节课的考勤信息
     * @param studentInfo 学生信息
     * @param courseId 课程信息
     * @return
     */
    @Override
    public WisdomResult getStudentAttOfCourse(StudentInfo studentInfo, String courseId) {
        return null;
    }

}
