package com.smart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.pojo.RequestLogin;
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
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
//@SessionAttributes(value = {"tbStudent","tbTeacher"})
public class LoginController {

    @Autowired
    private StudentLoginService studentLoginService;

    @Autowired
    private TeacherLoginService teacherLoginService;

    @Autowired
    private WXLoginService wxLoginService;
    //,method = RequestMethod.POST
    @RequestMapping("/all")
    @ResponseBody
    public WisdomResult webAppLogin(RequestLogin requestLogin, Model model, HttpServletRequest request){
        System.out.println(requestLogin);
        WisdomResult result = null;
        //判断一下是谁登陆
        if (requestLogin.getStatus() == 1){
            //学生，封装一下账号密码
            TbStudent tbStudent = new TbStudent();
            //账号
            tbStudent.setId(requestLogin.getAccount());
            //密码
            tbStudent.setPassword(requestLogin.getPassword());
            //调用service层,获取到结果
            result = studentLoginService.studentLoginOfWeb(tbStudent);
            //将获取到的值存进session中
            tbStudent = (TbStudent) result.getData();
            request.getSession().setAttribute("tbStudent",tbStudent);
        }else if (requestLogin.getStatus() == 2){
            //老师，封装一下账号密码
            TbTeacher tbTeacher = new TbTeacher();
            tbTeacher.setId(requestLogin.getAccount());
            tbTeacher.setPassword(requestLogin.getPassword());
            //调用service层
            result = teacherLoginService.teacherLoginOfWeb(tbTeacher);
            System.out.println(result);

        }
        return result;
    }

    /**
     * 微信小程序登陆，先要调用微信官网的api，固定参数
     * @param appid 小程序的id
     * @param secret 小程序的appSecret
     * @param js_code 登陆时获取的code
     * @return 返回值
     */
    @RequestMapping("/weixinLogin")
    @ResponseBody
    public WisdomResult weiXinLogin(String appid , String secret , String js_code , String grant_type, HttpServletRequest request)
    throws Exception{
        WisdomResult login = wxLoginService.login(appid, secret, js_code, grant_type);
        return login;
    }

    @RequestMapping("test")
    private void test(ModelMap modelMap){
        System.out.println(modelMap.get("tbStudent"));
    }

    @Test
    public void fun(){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET" +
                "&js_code=JSCODE&grant_type=authorization_code";
        HttpPost httpPost = new HttpPost();
    }
}
