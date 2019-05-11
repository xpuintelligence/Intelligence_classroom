package edu.xpu.tim.myfaceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xuexiang.xui.widget.toast.XToast;

import cz.msebera.android.httpclient.Header;
import edu.xpu.tim.myfaceapplication.config.AppConfig;
import edu.xpu.tim.myfaceapplication.util.net.CookieUtils;
import edu.xpu.tim.myfaceapplication.util.net.FinalAsyncHttpClient;

import static com.xuexiang.xui.XUI.getContext;

public class FirstRunAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //首次登陆
        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        Boolean isFirst = first.getBoolean("isFirst", true);
        if(isFirst){
            startActivity(new Intent(getApplicationContext(), StdRegAty.class));
            finish();
        }else{
            int status = first.getInt("status", -1);
            if(status == 1){

                AsyncHttpClient asyncHttpClient = new FinalAsyncHttpClient().getAsyncHttpClient();
                CookieUtils.saveCookie(asyncHttpClient,FirstRunAty.this);
                RequestParams params = new RequestParams();
                String retStr = first.getString("retStr", "");
                String id = JSONObject.parseObject(retStr).getJSONObject("data").getString("id");
                String password = JSONObject.parseObject(retStr).getJSONObject("data").getString("password");
                params.put("account", id);
                params.put("password",password);
                //学生\老师登陆
                params.put("status", 1);
                asyncHttpClient.post(AppConfig.loginAddress, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        CookieUtils.setCookies(CookieUtils.getCookie(FirstRunAty.this));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

                startActivity(new Intent(getApplicationContext(), StudentAty.class));
                finish();
            }else if(status == 2){

                AsyncHttpClient asyncHttpClient = new FinalAsyncHttpClient().getAsyncHttpClient();
                CookieUtils.saveCookie(asyncHttpClient,FirstRunAty.this);
                RequestParams params = new RequestParams();
                String retStr = first.getString("retStr", "");
                String id = JSONObject.parseObject(retStr).getJSONObject("data").getString("id");
                String password = JSONObject.parseObject(retStr).getJSONObject("data").getString("password");
                params.put("account", id);
                params.put("password",password);
                //学生\老师登陆
                params.put("status", 2);
                asyncHttpClient.post(AppConfig.loginAddress, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        CookieUtils.setCookies(CookieUtils.getCookie(FirstRunAty.this));
                        runOnUiThread(()-> {
                                XToast.success(getContext(), "跟新Session成功！！！").show();
                        });

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        runOnUiThread(()-> {
                            XToast.error(getContext(), "更新Session失败！！！").show();
                        });
                    }
                });

                startActivity(new Intent(getApplicationContext(), TeacherAty.class));
                finish();
            }
        }
        setContentView(R.layout.activity_first_run_aty);
    }
}
