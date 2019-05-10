package edu.xpu.tim.myfaceapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xuexiang.xui.widget.textview.MarqueeTextView;

import java.util.Arrays;
import java.util.List;

public class TeacherAty extends AppCompatActivity {
    private MarqueeTextView teaAty_name;
    final List<String> datas = Arrays.asList("欢迎您：XXX老师");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_teacher_aty);
        teaAty_name  = findViewById(R.id.teaAty_name);
        teaAty_name.startSimpleRoll(datas);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        teaAty_name.clear();
    }
}
