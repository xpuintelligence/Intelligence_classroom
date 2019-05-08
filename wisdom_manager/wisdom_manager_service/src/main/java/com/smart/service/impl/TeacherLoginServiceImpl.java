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
        TbTeacherExample tbTeacherExample = new TbTeacherExample();
        //附加条件
        TbTeacherExample.Criteria criteria = tbTeacherExample.createCriteria();
        //账号密码
        criteria.andIdEqualTo(tbTeacher.getId());
        criteria.andPasswordEqualTo(tbTeacher.getPassword());
        //获取查询结果
        List<TbTeacher> tbTeachers = tbTeacherMapper.selectByExample(tbTeacherExample);
        //查看查出来几条记录
        if (tbTeachers.size() == 1){
            //1条记录则返回改记录所对应的pojo
            WisdomResult result = WisdomResult.ok(tbTeachers.get(0));
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
        TbTeacherExample tbTeacherExample = new TbTeacherExample();
        TbTeacherExample.Criteria criteria = tbTeacherExample.createCriteria();
        //去数据库中查询是否存在
        criteria.andWexinIdEqualTo(openid);
        List<TbTeacher> tbTeachers = tbTeacherMapper.selectByExample(tbTeacherExample);
        //如果学生表中没有这个人
        if (tbTeachers.size() == 0){
            return new WisdomResult(0,openid,null);
        }
        return new WisdomResult(1,openid,tbTeachers.get(0));
    }
}
