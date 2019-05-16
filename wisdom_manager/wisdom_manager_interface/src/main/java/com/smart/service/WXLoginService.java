package com.smart.service;

import com.smart.pojo.WisdomResult;

import java.io.IOException;

public interface WXLoginService {
    /**
     * 去腾讯获取openid，然后进行登陆操作
     */
    public WisdomResult login(String appid , String secret , String js_code , String grant_type) throws IOException, Exception;

    /**
     * 第一次登陆，绑定openid与account
     */
    public WisdomResult bindOpenidWithAccount(String openid , String account , String password ,int status);

    public String test();
}
