package edu.xpu.tim.myfaceapplication.config;

public interface AppConfig {

    //网络控制
    boolean netWork = true;

    //采集精度控制
    int pictureCount = 3;

    //登录请求地址
    String loginAddress = "http://101.132.78.78:8080/wisdom_web/login/all";


    //获取本月的课程信息
    String classInfoAddress = "http://101.132.78.78:8080/wisdom_web/studentCourseInfo/thisMonth";
    String TAG = "MyAppConfig";
}