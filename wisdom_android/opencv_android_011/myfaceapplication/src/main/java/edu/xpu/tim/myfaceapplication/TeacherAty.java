package edu.xpu.tim.myfaceapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xui.widget.textview.MarqueeTextView;

import java.util.ArrayList;
import java.util.List;

public class TeacherAty extends AppCompatActivity {
    private MarqueeTextView teaAty_name;
    final List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_aty);
        initInfo();
    }

    private void initInfo() {
        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String stuInfoStr = first.getString("retStr", "");
        JSONObject teaInfo_jo = JSONObject.parseObject(stuInfoStr).getJSONObject("data");
        datas.add("欢迎您：" + teaInfo_jo.getString("name") + "老师");
        teaAty_name  = findViewById(R.id.teaAty_name);
        teaAty_name.startSimpleRoll(datas);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        teaAty_name.clear();
    }
}
