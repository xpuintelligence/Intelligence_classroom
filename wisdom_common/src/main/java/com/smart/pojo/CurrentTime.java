package com.smart.pojo;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * 时间类，推算与今天有关的时间信息
 */
public class CurrentTime implements Serializable {
    private DateTime now;    //当前时间

    //构造函数
    public CurrentTime() {
        now = new DateTime();
    }






}
