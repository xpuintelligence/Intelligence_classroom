package com.smart.controller;

import com.smart.pojo.RequestLogin;
import com.smart.pojo.TbStudent;
import com.smart.pojo.TbTeacher;
import com.smart.pojo.WisdomResult;
import com.smart.service.StudentLoginService;
import com.smart.service.TeacherLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
@SessionAttributes(value = {"tbStudent","tbTeacher"})
public class LoginController {

    @Autowired
    private StudentLoginService studentLoginService;

    @Autowired
    private TeacherLoginService teacherLoginService;
    //,method = RequestMethod.POST
    @RequestMapping(value = "all")
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
            model.addAttribute("tbStudent",tbStudent);

        }else if (requestLogin.getStatus() == 2){
            //老师，封装一下账号密码
            TbTeacher tbTeacher = new TbTeacher();
            tbTeacher.setId(requestLogin.getAccount());
            tbTeacher.setPassword(requestLogin.getPassword());
            //调用service层
            result = teacherLoginService.teacherLoginOfWeb(tbTeacher);
            System.out.println(result);
            //将获取到的teacherInfo放进session种
            tbTeacher = (TbTeacher) result.getData();
            model.addAttribute("tbTeacher",tbTeacher);

        }
        return result;
    }

    @RequestMapping("test")
    private void test(ModelMap modelMap){
        System.out.println(modelMap.get("tbStudent"));
    }

}
