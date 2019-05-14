package edu.xpu.tim.myfaceapplication.student;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


import java.util.List;

import cz.msebera.android.httpclient.cookie.Cookie;
import edu.xpu.tim.myfaceapplication.R;
import edu.xpu.tim.myfaceapplication.util.net.CookieUtils;

public class CheckSpecificInfoAty extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_specific_info_aty);
        TextView CheckSpecificInfoAty_tv = findViewById(R.id.CheckSpecificInfoAty_tv);


        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        CheckSpecificInfoAty_tv.setText(intent.getStringExtra("data"));


        String url = "http://106.12.202.93/#/testChart";
        WebView webChart = findViewById(R.id.webChart);
        webChart.getSettings().setJavaScriptEnabled(true);

        CookieManager cookieManager = CookieManager.getInstance();

        //如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可
        List<Cookie> cookies = CookieUtils.getCookie(getApplicationContext());
        cookieManager.setCookie(url, cookies.get(0).getName()+"="+cookies.get(0).getValue());
        //cookieManager.setCookie(url, "userData={\"id\":\"41604090109\",\"name\":\"邹长林\",\"sex\":\"male\",\"phone\":null,\"email\":null,\"classId\":\"080902201602\",\"className\":\"软件2班\",\"professionId\":\"080902\",\"professionName\":\"软件工程\",\"collegeId\":\"06\",\"collegeName\":\"计算机科学学院\",\"weixinId\":null}");

        webChart.loadUrl(url);
        webChart.setWebViewClient(new WebViewClient(){
            //重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }});
    }
}
