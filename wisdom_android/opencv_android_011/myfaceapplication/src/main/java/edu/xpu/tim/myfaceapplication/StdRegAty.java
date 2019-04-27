package edu.xpu.tim.myfaceapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StdRegAty extends Activity {
    private EditText et_id;
    private EditText et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_reg_aty);
        et_id = findViewById(R.id.stu_id);
        et_name = findViewById(R.id.stu_name);
    }


    public void toFace(View v){
        String name = et_name.getText().toString();
        String id = et_id.getText().toString();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.putExtra("name", name);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
