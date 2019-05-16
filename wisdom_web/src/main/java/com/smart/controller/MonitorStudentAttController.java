package com.smart.controller;

import com.smart.pojo.ServiceMsg;
import com.smart.pojo.WisdomResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    /**
     *  向消息队列里面添加消息
     * @param msg 消息
     * @param status status:1--->睡觉  2--->迟到
     * @param id 学生学号
     */
    @RequestMapping("/setStudentStatus")
    @ResponseBody
    public WisdomResult getMsgFromPrictureServer(String msg,String status , String id, HttpServletRequest request) {
        System.out.println(status + "+" + id);
        //获取消息队列
        ArrayList<ServiceMsg> msgQueue = (ArrayList<ServiceMsg>) servletContext.getAttribute("queue");
        //判读消息队列是否为空
        if (msgQueue == null) {
            msgQueue = new ArrayList<>();
        }
        //创建消息
        ServiceMsg serviceMsg = new ServiceMsg(id,status,msg);
        //放进消息队列中
        msgQueue.add(serviceMsg);
        servletContext.setAttribute("queue", msgQueue);
        System.out.println(msgQueue);
        return WisdomResult.ok();
    }

    /**
     * app获取消息队列
     * @param id 学生id
     */
    @RequestMapping("/getStudentMsg")
    @ResponseBody
    public WisdomResult sendStudentMsgToApp(String id,HttpServletRequest request){
        //获取消息队列
        ArrayList<ServiceMsg> msgQueue = (ArrayList<ServiceMsg>) servletContext.getAttribute("queue");
        System.out.println(msgQueue);
        //判读消息队列是否存在
        if (msgQueue == null){
            return new WisdomResult(0,"无该学生的消息",null);
        }
        //循环遍历看有没有这个学生的信息
        for (ServiceMsg msg : msgQueue) {
            if (msg.getId().equals(id)){
                //如果存在这个学生的id
                //先从消息队列里面删了
                ServiceMsg serviceMsg = new ServiceMsg(msg.getId(),msg.getStatus(),msg.getMsg());
                msgQueue.remove(msg);
                return WisdomResult.ok(serviceMsg);
            }
        }
        //没有这个学生的信息
        return new WisdomResult(0,"没有该学生的消息",null);
    }

}
