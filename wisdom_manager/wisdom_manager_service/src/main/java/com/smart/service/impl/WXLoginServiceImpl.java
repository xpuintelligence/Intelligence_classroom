package com.smart.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.pojo.TbStudent;
import com.smart.pojo.TbTeacher;
import com.smart.pojo.WisdomResult;

import com.smart.service.StudentLoginService;
import com.smart.service.TeacherLoginService;
import com.smart.service.WXLoginService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service("wXLoginServiceImpl")
public class WXLoginServiceImpl implements WXLoginService, Serializable {
    @Autowired
    private StudentLoginService studentLoginService;

    @Autowired
    private TeacherLoginService teacherLoginService;
    /**
     * 进行登陆操作
     */
    @Override
    public WisdomResult login(String appid, String secret, String js_code, String grant_type) throws Exception {
        //获取openid的url
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+
                "&secret="+secret+ "&js_code="+js_code;
        //判断最后一个参数是否存在，有的话加上
        if (grant_type != null)
            url += "&grant_type="+grant_type;
        //发送get请求
        OkHttpClient client = new OkHttpClient();
        Request request1 = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request1);
        //获取请求结果
        Response response = call.execute();
        //获取请求体字符串
        String json = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();
        //将json字符串转为map对象
        Map<String, Object> tmpMap = objectMapper.readValue(json, Map.class);
        //将openid放进tbstudent
        TbStudent tbStudent = new TbStudent();
        tbStudent.setWexinId((String) tmpMap.get("openid"));
        //将openid放进tbTeacher
        TbTeacher tbTeacher = new TbTeacher();
        tbTeacher.setWexinId((String) tmpMap.get("openid"));
        //调用俩个人的方法进行查询
        WisdomResult result = studentLoginService.studentLoginOfWX(tbStudent);
        //判断一下是不是在学生中,不是再去老师中判断
        if (result.getStatus() == 0) {
            result = teacherLoginService.teacherLoginOfWX(tbTeacher);
        }
        //判断一下是不是在老师中，如果不是在老师中，那么要告诉微信端这个人没有绑定
        if (result.getStatus() == 0 ) {
            result = new WisdomResult(0,"无该人员信息，请进行账号密码绑定",null);
        }
        return result;
    }
}
