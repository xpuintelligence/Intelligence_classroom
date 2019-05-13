package edu.xpu.tim.myfaceapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleGuideBanner;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.ArrayList;

import edu.xpu.tim.myfaceapplication.student.StuCheckInfoAty;

import static com.xuexiang.xui.XUI.getContext;

public class StudentAty extends AppCompatActivity {
    private static final String TAG = "StudentAty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_aty);
        initStudentInfo();
    }

    //登陆后的信息展示
    private void initStudentInfo() {

        //轮播图
        SimpleGuideBanner guideBanner = findViewById(R.id.stuLoopView);
        guideBanner.setAutoScrollEnable(true)
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(10f)
                .setSelectAnimClass(ZoomInEnter.class)
                .barPadding(0, 5, 0, 0)
                .setSource(getUsertGuides())
                .startScroll();


        TextView stuAty_id = findViewById(R.id.stuAty_id);
        TextView stuAty_name = findViewById(R.id.stuAty_name);
        TextView stuAty_class = findViewById(R.id.stuAty_class);
        TextView stuAty_collegeName = findViewById(R.id.collegeName);

        //积分模块
        TextView stuAty_jifen = findViewById(R.id.stuAty_jifen);

        SharedPreferences first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String stuInfoStr = first.getString("retStr", "");
        Log.i(TAG, "stuInfoStr:" + stuInfoStr);
        try{
            JSONObject retData = JSONObject.parseObject(stuInfoStr);
            JSONObject stu = retData.getJSONObject("data");
            stuAty_id.setText(stu.getString("id"));
            stuAty_name.setText(stu.getString("name"));
            stuAty_class.setText(stu.getString("className"));
            stuAty_collegeName.setText(stu.getString("collegeName"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //返回退出确认
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(R.drawable.icon_tip)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        StudentAty.this.finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();



//        new MaterialDialog.Builder(getContext())
//                .content(R.string.tip_bluetooth_permission)
//                .positiveText(R.string.lab_yes)
//                .negativeText(R.string.lab_no)
//                .show();
    }


    //轮播图的资源
    public static ArrayList<Integer> getUsertGuides() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.loop_01);
        list.add(R.drawable.loop_02);
        list.add(R.drawable.loop_03);
        list.add(R.drawable.loop_01);
        return list;
    }


    //开启考勤信息
    public void toCheckInfoAty(View view) {
        startActivity(new Intent(getApplicationContext(),StuCheckInfoAty.class));
    }
}
