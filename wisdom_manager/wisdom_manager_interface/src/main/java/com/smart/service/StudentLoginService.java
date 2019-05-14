package com.smart.service;

import com.smart.pojo.StudentInfo;
import com.smart.pojo.TbStudent;
import com.smart.pojo.WisdomResult;

/**
 * 学生登陆service
 */
public interface StudentLoginService {
    /**
     * 通过web端登陆学生端
     */
    WisdomResult studentLoginOfWeb(TbStudent tbStudent);

    /**
     * 通过小程序端登陆学生端
     */
    WisdomResult studentLoginOfWX(StudentInfo studentInfo);
}
