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

import java.util.ArrayList;
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
        //格式化时间
        DateTime startTime = DateUtils.stringToDatetime(start);
        DateTime endTime = DateUtils.stringToDatetime(end);
        return getAttASpellTime(startTime,endTime,teacherInfo);
    }

    @Override
    public WisdomResult getAttASpellTime(DateTime startTime, DateTime endTime, TeacherInfo teacherInfo) {
        startTime = DateUtils.formatSpellTime(startTime).get("start");
        endTime = DateUtils.formatSpellTime(endTime).get("end");
        //创建一个list
        List<CheckAttendanceStatistic> lists = new ArrayList<>();
        //把每天的课程结果都拿出
        while (!endTime.isEqual(startTime)){
            Map<String, DateTime> map = DateUtils.formatSpellTime(startTime);
            //获取这段时间内的每节课的考勤信息
            List<CheckAttendance> checkAttendances = tbStudentMapper.queryCheckAttendance(map.get("start").toString(DATE_FORMAT_TOSTRING),
                    map.get("end").toString(DATE_FORMAT_TOSTRING), teacherInfo.getId());
            //判断一下今天有没有课，没课就下一天
            if (checkAttendances == null || checkAttendances.size() == 0){
                System.out.println(map.get("start").toString("yyyy-mm-dd")+"没课");
                //将时间+1
                startTime = startTime.plusDays(1);
                continue;
            }
            //对这节课进行分装,以及对信息进行初始化
            CheckAttendanceStatistic checkAttendanceStatistic = new CheckAttendanceStatistic(checkAttendances);
            //将封装好的结果放进去
            lists.add(checkAttendanceStatistic);
            System.out.println(map.get("start").toString("yyyy-mm-dd"));
            //将时间+1
            startTime = startTime.plusDays(1);
        }
        //如果没课，返回没课
        if (lists.size() == 0)
            return new WisdomResult(2,"今天没有课程",null);
        return WisdomResult.ok(lists);
    }

    /**
     * 获取今天上课的考勤信息每一节课的考勤信息
     * @param teacherInfo 教师信息
     * @return
     */
    @Override
    public WisdomResult getAttToday(TeacherInfo teacherInfo) {
        return getAttASpellTime(DateUtils.getCurrentTime(),DateUtils.getCurrentTime(),teacherInfo);
    }

    /**
     * 本周每节课的考勤信息
     * @param teacherInfo
     * @return
     */
    @Override
    public WisdomResult getAttThisWeek(TeacherInfo teacherInfo) {
        DateTime currentTime = DateUtils.getCurrentTime();
        Integer integer = courseService.dayOfWeekInThisSemester(currentTime);
        Map<String, DateTime> nWeekInThisSemester = courseService.getNWeekInThisSemester(integer);
        //获取这段时间的
        return getAttASpellTime(nWeekInThisSemester.get("start"), nWeekInThisSemester.get("end"), teacherInfo);
    }

    /**
     * 本月每节课的考勤信息
     * @param teacherInfo
     * @return
     */
    @Override
    public WisdomResult getAttThisMont(TeacherInfo teacherInfo) {
        DateTime dateTime = DateUtils.getCurrentTime();
        Integer integer = courseService.dayOfMonthInThisSemester(dateTime);
        Map<String, DateTime> nMonthInThisSemester = courseService.getNMonthInThisSemester(integer);
        return getAttASpellTime(nMonthInThisSemester.get("start"),nMonthInThisSemester.get("end"),teacherInfo);
    }

    /**
     * 这个学期每节课的考勤信息
     * @param teacherInfo
     * @return
     */
    @Override
    public WisdomResult getAttThisSemester(TeacherInfo teacherInfo) {
        DateTime opentime = courseService.getOpentime();
        DateTime currentTime = DateUtils.getCurrentTime();
        return getAttASpellTime(opentime,currentTime,teacherInfo);
    }

    /**
     * 某一天的考勤信息
     * @param day 某一天的时间
     * @return
     */
    @Override
    public WisdomResult getAttOfOneDay(String day , TeacherInfo teacherInfo) {
        DateTime dateTime = DateUtils.stringToDatetime(day);
        Map<String, DateTime> map = DateUtils.formatSpellTime(dateTime);
        return getAttASpellTime(map.get("start"),map.get("end"),teacherInfo);
    }

    /**
     * 某一节课整个班级的考勤信息
     * @param crouseItemId 某一节课的id
     */
    @Override
    public WisdomResult getAttOfClass(String crouseItemId) {
        List<AttendanceCourseItem> attendanceCourseItems = tbStudentMapper.queryAttendanceCourseItemByCourseitem_id(crouseItemId);
        return WisdomResult.ok(attendanceCourseItems);
    }

    /**
     * 某一个学生这一学期这节课的考勤信息
     * @param studentId 学生信息
     * @param courseId 课程信息
     * @return
     */
    @Override
    public WisdomResult getStudentAttOfCourse(String studentId, String courseId) {
        CourseAVGAttendance courseAVGAttendance = tbStudentMapper.queryCourseAVGAttendance(studentId, courseId);
        return WisdomResult.ok(courseAVGAttendance);
    }

}
