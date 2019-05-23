package edu.xpu.tim.myfaceapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;
import edu.xpu.tim.myfaceapplication.config.AppConfig;
import edu.xpu.tim.myfaceapplication.util.net.CookieUtils;
import edu.xpu.tim.myfaceapplication.util.net.FinalAsyncHttpClient;
import edu.xpu.tim.myfaceapplication.util.net.GetPostUrl;

import static com.xuexiang.xui.XUI.getContext;

public class FirstRunAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //首次登陆
        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = first.edit();
        //开启检测服务
        Timer timer = new Timer();
        Vibrator mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        new Thread(()-> {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Map<String, String> map = new HashMap<>();
                    String id = first.getInt("status", -1) == 1 ?
                            first.getString("stu_id", "-1"):first.getString("tea_id", "-1");
                    assert id != null;
                    Log.i(AppConfig.TAG, "SP获取的id = " + id);
                    map.put("id", id);

                    //请求的时候try-catch
                    try {
                        String post = GetPostUrl.post(AppConfig.nowSmg, map);
                        JSONObject object = JSONObject.parseObject(post);
                        Log.i(AppConfig.TAG, "post = " + post);
                        Integer status = object.getInteger("status");
                        if (status == 0) {
                            String msg = object.getString("msg");
                            Log.i(AppConfig.TAG, "msg:" + msg);
                        } else if (status == 1) {
                            JSONObject data = object.getJSONObject("data");
                            Log.i(AppConfig.TAG, "data = "+data);
                            String status1 = data.getString("status");
                            if ("1".equals(status1)) {
                                //学生在睡觉，应该提醒
                                runOnUiThread(() -> XToast.info(getContext(), "检测到你正在睡觉！").show());
                                //停止1秒，开启震动10秒，然后又停止2秒，又开启震动10秒，不重复
                                mVibrator.vibrate(new long[]{1000, 10000, 1000, 10000}, -1);
                            } else if ("2".equals(status1)) {
                                //已经获取到出勤的学生名单
                                editor.putString("attendance2", data.getString("msg"));
                                editor.apply();
                            } else if ("3".equals(status1)) {
                                //已经获取到旷课学生名单
                                editor.putString("attendance3", data.getString("msg"));
                                editor.apply();
                            } else {
                                //其他情况
                                Log.e(AppConfig.TAG, data.getString("msg"));
                            }
                        }
                    } catch (Exception e) {
                        Log.i(AppConfig.TAG, "服务器端严重错误：" + e.toString());
                    }

                }
            }, new Date(), 2000);
        }).start();

        Boolean isFirst = first.getBoolean("isFirst", true);
        if(isFirst){
            startActivity(new Intent(getApplicationContext(), StdRegAty.class));
            finish();
        }else{
            int status = first.getInt("status", -1);
            if(status == 1){
                AsyncHttpClient asyncHttpClient = new FinalAsyncHttpClient().getAsyncHttpClient();
                CookieUtils.saveCookie(asyncHttpClient,getApplicationContext());
                RequestParams params = new RequestParams();
                String retStr = first.getString("retStr", "");
                String id = JSONObject.parseObject(retStr).getJSONObject("data").getString("id");
                String stu_pwd = first.getString("stu_pwd", "");
                //String password = JSONObject.parseObject(retStr).getJSONObject("data").getString("password");
                params.put("account", id);
                params.put("password",stu_pwd);
                //学生\老师登陆
                params.put("status", 1);
                asyncHttpClient.post(AppConfig.loginAddress, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        CookieUtils.setCookies(CookieUtils.getCookie(getApplicationContext()));
                        Log.i(AppConfig.TAG, new String(responseBody));
                        XToast.success(getContext(), "学生端更新Session成功!").show();
                        List<Cookie> cookies = CookieUtils.getCookie(getApplicationContext());

                        for (Cookie cookie: cookies) {
                            Log.i(AppConfig.TAG, cookie.getName()+":"+cookie.getValue());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        XToast.success(getContext(), "学生端更新Session失败！！！statusCode= " + statusCode).show();
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
                String tea_pwd = first.getString("tea_pwd", "");

                //String password = JSONObject.parseObject(retStr).getJSONObject("data").getString("password");
                params.put("account", id);
                params.put("password",tea_pwd);
                //学生\老师登陆
                params.put("status", 2);
                asyncHttpClient.post(AppConfig.loginAddress, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        CookieUtils.setCookies(CookieUtils.getCookie(FirstRunAty.this));
                        XToast.success(getContext(), "教师端更新Session成功!").show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        XToast.error(getContext(), "教师端更新Session失败!").show();
                    }
                });

                startActivity(new Intent(getApplicationContext(), TeacherAty.class));
                finish();
            }
        }
        setContentView(R.layout.activity_first_run_aty);
    }
}