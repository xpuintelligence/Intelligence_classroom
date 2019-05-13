package edu.xpu.tim.myfaceapplication.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.xpu.tim.myfaceapplication.R;

public class CheckSpecificInfoAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_specific_info_aty);
        TextView CheckSpecificInfoAty_tv = findViewById(R.id.CheckSpecificInfoAty_tv);

        Intent intent = getIntent();
        CheckSpecificInfoAty_tv.setText(intent.getStringExtra("data"));
    }
}
