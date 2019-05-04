package com.smart.service.impl;

import com.smart.mapper.TbCourseitemMapper;
import com.smart.mapper.TbTeacherMapper;
import com.smart.pojo.*;
import com.smart.service.TeacherLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherLoginServiceImpl implements TeacherLoginService {

    @Autowired
    private TbTeacherMapper tbTeacherMapper;

    @Autowired
    private TbCourseitemMapper tbCourseitemMapper;
    @Override
    public WisdomResult teacherLoginOfWeb(TbTeacher tbTeacher) {
        TbCourseitem tbCourseitem = new TbCourseitem();
        TbCourseitemExample tbCourseitemExample = new TbCourseitemExample();
        TbCourseitemExample.Criteria criteria1 = tbCourseitemExample.createCriteria();
        criteria1.andTimeNotEqualTo(new Date());
        List<TbCourseitem> tbCourseitems = tbCourseitemMapper.selectByExample(tbCourseitemExample);
        System.out.println(tbCourseitems.size());

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
}
