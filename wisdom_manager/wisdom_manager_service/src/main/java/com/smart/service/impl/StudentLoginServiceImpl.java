package com.smart.service.impl;

import com.smart.mapper.TbStudentMapper;
import com.smart.pojo.*;
import com.smart.service.StudentLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service

public class StudentLoginServiceImpl implements StudentLoginService, Serializable {
    @Autowired
    private TbStudentMapper tbStudentMapper ;


    /**
     * 通过对应tbS
     */
    @Override
    public WisdomResult studentLoginOfWeb(TbStudent tbStudent) {

        //查询结果
        StudentInfo studentInfo = tbStudentMapper.selectStudentIdAndPassword(tbStudent.getId(), tbStudent.getPassword());
        //查看查出来几条记录
        if (studentInfo != null){
            //1条记录则返回改记录所对应的pojo
            WisdomResult result = WisdomResult.ok(studentInfo);
            return result;
        }
        //不是一条记录返回false
        return WisdomResult.build(0,"账号或密码错误");
    }

    @Override
    public WisdomResult studentLoginOfWX(StudentInfo studentInfo) {
        //拿到student以后，判断openid是不是在数据库中存在
        String openid = studentInfo.getWeixinId();
        //去数据库中查询是否存在
        studentInfo = tbStudentMapper.selectStudentInfo(openid);
        //如果学生表中没有这个人
        if (studentInfo == null){
            return new WisdomResult(0,openid,null);
        }
        return new WisdomResult(1,openid,studentInfo);
    }




}
