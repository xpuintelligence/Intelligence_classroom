package com.smart.service;

import com.smart.pojo.TbTeacher;
import com.smart.pojo.WisdomResult;

/**
 * teacher登陆所对应的service
 */
public interface TeacherLoginService {
    /**
     * 老师账号密码登陆
     */
    public WisdomResult teacherLoginOfWeb(TbTeacher tbTeacher);

    /**
     * 老师通过openid登陆
     */
    public WisdomResult teacherLoginOfWX(TbTeacher tbTeacher);

}
