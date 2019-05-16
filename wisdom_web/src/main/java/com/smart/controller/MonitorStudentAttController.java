package com.smart.controller;

import com.smart.pojo.ServiceMsg;
import com.smart.pojo.WisdomResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 学生考勤监听
 */
@RequestMapping("/monitorStudent")
@Controller
public class MonitorStudentAttController {
    @Autowired
    private ServletContext servletContext;
    @RequestMapping("/setStudentStatus")
    /**
     * status:1--->睡觉  2--->迟到
     * id:哪个学生
     */
    public void getMsgFromPrictureServer(Integer status , String id, HttpServletRequest request) {
        System.out.println(status + "+" + id);
        //获取消息队列
        ArrayList<ServiceMsg> msgQueue = (ArrayList<ServiceMsg>) servletContext.getAttribute("queue");
        //判读消息队列是否为空
        if (msgQueue == null) {
            msgQueue = new ArrayList<>();
        }
        //创建消息
        ServiceMsg msg = new ServiceMsg();
        msg.setId(id);
        msg.setStatus(status);
        //放进消息队列中
        msgQueue.add(msg);
        servletContext.setAttribute("queue", msgQueue);
        System.out.println(msgQueue);
    }
}
