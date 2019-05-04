package edu.xpu.tim.myfaceapplication.config;

public interface AppConfig {
    //网络控制
    boolean netWork = true;

    //采集精度控制
    int pictureCount = 10;

    //登录请求地址
    String loginAddress = "http://47.103.14.73/wisdom_web/login/all";


}
