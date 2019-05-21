package edu.xpu.tim.myfaceapplication.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.xpu.tim.myfaceapplication.R;

public class AttendanceTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_teacher);
    }

    public void getAttendanceInfo(View v){
        TextView tv = findViewById(R.id.tv_AttendanceTeacher_show);
        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String attendance2 = first.getString("attendance2", "");
        String attendance3 = first.getString("attendance3", "");
        tv.setText("出勤学生："+ attendance2 + "\n" + "未到学生：" + attendance3);
    }
}
