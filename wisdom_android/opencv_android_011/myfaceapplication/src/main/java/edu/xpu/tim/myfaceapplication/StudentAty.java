package edu.xpu.tim.myfaceapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

public class StudentAty extends AppCompatActivity {
    private static final String TAG = "StudentAty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_aty);

        TextView viewById = findViewById(R.id.stuInfo);
        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String retStr = first.getString("retStr", "");
        Log.i(TAG, retStr);
        JSONObject jsonObject = JSONObject.parseObject(retStr);
        JSONObject data = jsonObject.getJSONObject("data");
        Log.i(TAG, data.toJSONString());
        viewById.setText(data.getString("name") + " " + data.getString("id"));
    }
}
