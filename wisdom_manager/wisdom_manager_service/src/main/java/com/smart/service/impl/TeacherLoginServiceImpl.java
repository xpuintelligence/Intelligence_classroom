package com.smart.service.impl;

import com.smart.mapper.TbTeacherMapper;
import com.smart.pojo.*;
import com.smart.service.TeacherLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class TeacherLoginServiceImpl implements TeacherLoginService, Serializable {

    @Autowired
    private TbTeacherMapper tbTeacherMapper;

    @Override
    public WisdomResult teacherLoginOfWeb(TbTeacher tbTeacher) {

        //获取查询结果
        TeacherInfo teacherInfo = tbTeacherMapper.selectTeacherIdAndPassword(tbTeacher.getId(), tbTeacher.getPassword());
        //查看查出来几条记录
        if (teacherInfo != null){
            //1条记录则返回改记录所对应的pojo
            WisdomResult result = new WisdomResult(2,"true",teacherInfo);
            return result;
        }
        //不是一条记录返回false
        return WisdomResult.build(0,"账号或密码错误");
    }

    @Override
    public WisdomResult teacherLoginOfWX(TbTeacher tbTeacher) {
        //拿到student以后，判断openid是不是在数据库中存在
        String openid = tbTeacher.getWexinId();
        //创建查询实例

        //去数据库中查询是否存在
        TeacherInfo teacherInfo = tbTeacherMapper.selectTeacherInfo(tbTeacher.getWexinId());
        //如果学生表中没有这个人
        if (teacherInfo == null){
            return new WisdomResult(0,openid,null);
        }
        return new WisdomResult(2,openid,teacherInfo);
    }
}
