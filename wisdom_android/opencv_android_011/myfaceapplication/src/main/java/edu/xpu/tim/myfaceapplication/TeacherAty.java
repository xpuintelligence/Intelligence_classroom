package edu.xpu.tim.myfaceapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleGuideBanner;
import com.xuexiang.xui.widget.textview.MarqueeTextView;

import java.util.ArrayList;
import java.util.List;

import edu.xpu.tim.myfaceapplication.teacher.AttendanceTeacher;

import static com.xuexiang.xui.XUI.getContext;

public class TeacherAty extends AppCompatActivity {
    private MarqueeTextView teaAty_name;
    List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_aty);
        initInfo();
    }

    private void initInfo() {
        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);

        //轮播图
        SimpleGuideBanner guideBanner = findViewById(R.id.stuLoopView2);
        guideBanner.setAutoScrollEnable(true)
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(10f)
                .setSelectAnimClass(ZoomInEnter.class)
                .barPadding(0, 5, 0, 0)
                .setSource(getUserGuides())
                .startScroll();

        String stuInfoStr = first.getString("retStr", "");
        JSONObject teaInfo_jo = JSONObject.parseObject(stuInfoStr).getJSONObject("data");
        datas.add("欢迎您：" + teaInfo_jo.getString("name") + "老师");
        teaAty_name  = findViewById(R.id.teaAty_name);
        teaAty_name.startSimpleRoll(datas);
    }
    public void toTeaAttendance(View view){
        //去课堂考勤页面
        startActivity(new Intent(getContext(), AttendanceTeacher.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        teaAty_name.clear();
    }

    //轮播图的资源
    public static ArrayList<Integer> getUserGuides() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.loop_01);
        list.add(R.drawable.loop_02);
        list.add(R.drawable.loop_03);
        list.add(R.drawable.loop_01);
        return list;
    }

    //返回退出确认
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(R.drawable.icon_tip)
                .setPositiveButton("确定", (dialog, which) -> {
                    // 点击“确认”后的操作
                    TeacherAty.this.finish();
                })
                .setNegativeButton("返回", (dialog, which) -> {
                    // 点击“返回”后的操作,这里不设置没有任何操作
                }).show();
    }
}
