package edu.xpu.tim.myfaceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.toast.XToast;

import cz.msebera.android.httpclient.Header;
import edu.xpu.tim.myfaceapplication.config.AppConfig;
import edu.xpu.tim.myfaceapplication.util.net.CookieUtils;
import edu.xpu.tim.myfaceapplication.util.net.FinalAsyncHttpClient;

import static com.xuexiang.xui.XUI.getContext;

public class StdRegAty extends AppCompatActivity {
    private EditText et_id;
    private EditText et_pwd;
    private static final String TAG = "StdRegAty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是第一次则正常加载
        setContentView(R.layout.activity_std_reg_aty);

        et_id = findViewById(R.id.stu_id);
        et_pwd = findViewById(R.id.stu_pwd);
    }

    public void toFace(View v){
        Button regTeaBtn = findViewById(R.id.regTeaBtn);
        regTeaBtn.setEnabled(false);
        someInfoLogin(true, new Intent(getApplicationContext(), MainActivity.class), regTeaBtn);
    }


    public void toTeacher(View v){
        Button regStuBtn = findViewById(R.id.regStuBtn);
        regStuBtn.setEnabled(false);
        someInfoLogin(false, new Intent(getApplicationContext(), TeacherAty.class), regStuBtn);
    }

    private void someInfoLogin(boolean isStu, Intent atyIntent, Button btn){
        String pwd = et_pwd.getText().toString();
        String id = et_id.getText().toString();

        //先检测是否为空
        if(TextUtils.isEmpty(pwd) || TextUtils.isEmpty(id)) {
            Toast.makeText(getApplicationContext(), "请输入完整信息", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = first.edit();
        if(isStu){
            edit.putString("stu_id", id);
            edit.putString("stu_pwd", pwd);
            //1表示学生 2表示老师
            edit.putInt("status", 1);
        }else{
            edit.putString("tea_id", id);
            edit.putString("tea_pwd", pwd);
            //1表示学生 2表示老师
            edit.putInt("status", 2);
            //老师登录无需刷脸
            edit.putBoolean("isFirst", false);
        }
        //以后都不再是首次登录
        //留到人脸识别在做
        //edit.putBoolean("isFirst", false);

        atyIntent.putExtra("pwd", pwd);
        atyIntent.putExtra("id", id);


        //调用远程登录方法
        //AsyncHttp开源项目提交数据到服务器
        if(AppConfig.netWork){
            AsyncHttpClient asyncHttpClient = new FinalAsyncHttpClient().getAsyncHttpClient();
            CookieUtils.saveCookie(asyncHttpClient,StdRegAty.this);
            //AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            //准备数据包
            RequestParams params = new RequestParams();
            params.put("account",id);
            params.put("password",pwd);

            //学生\老师登陆
            params.put("status", isStu ? 1:2);

            //准备请求地址
            asyncHttpClient.post(AppConfig.loginAddress, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try{
                        String retStr = new String(responseBody);

                        Log.i(TAG, retStr);
                        JSONObject retJson = JSONObject.parseObject(retStr);
                        Integer status = retJson.getInteger("status");
                        if(1 == status){
                            edit.putString("retStr", retStr);
                            if(edit.commit()) Log.i(TAG,"edit.commit() success!!!");

                            XToast.success(getContext(), "登录成功").show();
                            CookieUtils.setCookies(CookieUtils.getCookie(StdRegAty.this));
                            //先结束掉此Activity
                            startActivity(atyIntent);
                            finish();
                        }else if(0 == status){
                            XToast.error(getContext(), "用户名或密码不正确").show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        XToast.error(getContext(), "Web端严重错误！！！").show();
                    }finally {
                        btn.setEnabled(true);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    XToast.error(getContext(), "网络堵塞").show();
                    btn.setEnabled(true);
                }
            });
        }else{
            //无网络状态下演示用
            startActivity(atyIntent);
            finish();
        }
    }
}
