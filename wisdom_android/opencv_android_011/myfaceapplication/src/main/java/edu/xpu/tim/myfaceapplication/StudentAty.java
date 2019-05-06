package edu.xpu.tim.myfaceapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.xpu.tim.myfaceapplication.fragment.student.SFragmentHome;
import edu.xpu.tim.myfaceapplication.fragment.student.SFragmentMain;
import edu.xpu.tim.myfaceapplication.fragment.student.SFragmentMy;


public class StudentAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_aty);
        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container_student)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(SFragmentHome.class,
                        "首页",
                        R.drawable.item1_before,
                        R.drawable.item1_after)
                .addItem(SFragmentMain.class,
                        "数据",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(SFragmentMy.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();
    }
}
