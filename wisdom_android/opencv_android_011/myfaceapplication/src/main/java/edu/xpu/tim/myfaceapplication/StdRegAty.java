package edu.xpu.tim.myfaceapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class StdRegAty extends Activity {
    private EditText et_id;
    private EditText et_pwd;
    private SharedPreferences first;
    private static final String TAG = "StdRegAty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        if(first.getBoolean("isFirst", false)){
            //如果不是第一次登陆就直接跳转主页面
            String stu_id = first.getString("stu_id", "");
            String stu_pwd = first.getString("stu_pwd", "");

            Intent intent = new Intent(getApplicationContext(), AppleAty.class);
            intent.putExtra("id", stu_id);
            intent.putExtra("pwd", stu_pwd);

            startActivity(intent);
        }

        //是第一次则正常加载
        setContentView(R.layout.activity_std_reg_aty);
        et_id = findViewById(R.id.stu_id);
        et_pwd = findViewById(R.id.stu_pwd);
    }

    public void toFace(View v){
        String pwd = et_pwd.getText().toString();
        String id = et_id.getText().toString();
        SharedPreferences.Editor edit = first.edit();
        edit.putString("stu_id", id);
        edit.putString("stu_pwd", pwd);
        //以后都不再是首次登录
        edit.putBoolean("isFirst", true);

        if(edit.commit()) Log.i(TAG,"edit.commit() success!!!");

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("pwd", pwd);
        intent.putExtra("id", id);

        //先结束掉此Activity
        startActivity(intent);
        onStop();
    }
}
