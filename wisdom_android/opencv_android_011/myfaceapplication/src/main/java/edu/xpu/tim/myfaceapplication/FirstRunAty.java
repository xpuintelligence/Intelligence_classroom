package edu.xpu.tim.myfaceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
                startActivity(new Intent(getApplicationContext(), StudentAty.class));
                finish();
            }else if(status == 2){
                startActivity(new Intent(getApplicationContext(), TeacherAty.class));
                finish();
            }
        }
        setContentView(R.layout.activity_first_run_aty);
    }
}
