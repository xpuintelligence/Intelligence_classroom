package com.smart.controller;

import com.smart.service.WXLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class test {
@Autowired
private WXLoginService wxLoginServiceImpl;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return wxLoginServiceImpl.test();
    }
}
