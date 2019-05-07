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
        TbStudentExample tbStudentExample = new TbStudentExample();
        TbStudentExample.Criteria criteria = tbStudentExample.createCriteria();
        //判断账号密码是否存在
        criteria.andIdEqualTo(tbStudent.getId());
        criteria.andPasswordEqualTo(tbStudent.getPassword());
        //获取查询条件
        List<TbStudent> tbStudents = tbStudentMapper.selectByExample(tbStudentExample);
        //new一个结果集
        //查看查出来几条记录
        if (tbStudents.size() == 1){
            //1条记录则返回改记录所对应的pojo
            WisdomResult result = WisdomResult.ok(tbStudents.get(0));
            return result;
        }
        //不是一条记录返回false
        return WisdomResult.build(0,"账号或密码错误");
    }

    @Override
    public WisdomResult studentLoginOfWX(TbStudent tbStudent) {
        //拿到student以后，判断openid是不是在数据库中存在
        String openid = tbStudent.getWexinId();
        //创建查询实例
        TbStudentExample tbStudentExample = new TbStudentExample();
        TbStudentExample.Criteria studentExampleCriteria = tbStudentExample.createCriteria();
        //去数据库中查询是否存在
        studentExampleCriteria.andWexinIdEqualTo(openid);
        List<TbStudent> tbStudents = tbStudentMapper.selectByExample(tbStudentExample);
        //如果学生表中没有这个人
        if (tbStudents.size() == 0){
            return new WisdomResult(0,"该用户不为学生或未绑定",null);
        }
        return new WisdomResult(1,"true",tbStudents.get(0));
    }




}
