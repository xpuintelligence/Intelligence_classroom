package edu.xpu.tim.myfaceapplication.config;

public interface AppConfig {

    //网络控制
    boolean netWork = true;

    //采集精度控制
    int pictureCount = 20;

    //登录请求地址
    String loginAddress = "http://47.103.14.73:8080/wisdom_web/login/all";

    //获取本月的课程信息
    String classInfoAddress = "http://47.103.14.73:8080/wisdom_web/studetnAttendance/allCourseAttASpellTime";

    //获取消息队列
    String nowSmg = "http://47.103.14.73:8080/wisdom_web/monitorStudent/getStudentMsg";

    String TAG = "MyAppConfig";
}