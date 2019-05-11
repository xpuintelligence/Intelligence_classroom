package edu.xpu.tim.myfaceapplication.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import edu.xpu.tim.myfaceapplication.R;
import edu.xpu.tim.myfaceapplication.config.AppConfig;
import edu.xpu.tim.myfaceapplication.entity.AttendanceItem;
import edu.xpu.tim.myfaceapplication.util.net.FinalAsyncHttpClient;
import edu.xpu.tim.myfaceapplication.util.ui.DividerItemDecoration;

import static com.xuexiang.xui.XUI.getContext;

public class StuCheckInfoAty extends AppCompatActivity {
    private SharedPreferences first;
    private RecyclerView stuAty_checkAtyRv;
    private List<AttendanceItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_check_info_aty);

        first = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        initData();

        //通过findViewById拿到RecyclerView实例
        stuAty_checkAtyRv = findViewById(R.id.stuAty_checkAtyRv);

        //设置RecyclerView管理器
        stuAty_checkAtyRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //初始化适配器
        MyRecyclerViewAdapter myAdapter = new MyRecyclerViewAdapter(list);
        //设置添加或删除item时的动画，这里使用默认动画
        stuAty_checkAtyRv.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        stuAty_checkAtyRv.setAdapter(myAdapter);
        //添加横线
        stuAty_checkAtyRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        getData();
    }

    private void initData() {
        AttendanceItem attendanceItem = new AttendanceItem();
        attendanceItem.setName("Kafka源码剖析");
        attendanceItem.setClassroom_id("A1010");
        attendanceItem.setTime("2018年02月30日 14:00");
        attendanceItem.setTeacher_name("Tim老师");
        attendanceItem.setCourse_node("kafka源码剖析源码剖析让同学们明白Kafka的工作原理, 并对Kafka进行性能优化，Kafka在高并发下的吞吐量控制");
        attendanceItem.setGoal("总分：88.9");
        attendanceItem.setHead_up_rate("抬头率：90.7%");
        //TODO 出勤判断 attendanceItem.set
        for (int i = 0; i < 100; i++) {
            list.add(attendanceItem);
        }
    }

    private void getData() {
        AsyncHttpClient asyncHttpClient = new FinalAsyncHttpClient().getAsyncHttpClient();
        //准备数据包
        asyncHttpClient.post(AppConfig.classInfoAddress, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String classInfoStr = new String(responseBody);
                Log.i(AppConfig.TAG, classInfoStr);
                XToast.info(getContext(), classInfoStr).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}


class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<AttendanceItem> list;

    public MyRecyclerViewAdapter(List<AttendanceItem> list) {
        this.list = list;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stu_classinfo, parent, false);
        MyRecyclerViewAdapter.ViewHolder viewHolder = new MyRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.item_ClassName.setText(list.get(position).getName());
        holder.item_ClassAddress.setText(list.get(position).getClassroom_id());
        holder.item_ClassTime.setText(list.get(position).getTime() + "");
        holder.item_ClassTeacherName.setText(list.get(position).getTeacher_name());
        holder.item_ClassDescription.setText(list.get(position).getCourse_node());
        holder.item_ClassGoalScore.setText(list.get(position).getGoal());
        holder.item_ClassHeadUp.setText(list.get(position).getHead_up_rate());
        holder.item_ClassCheck.setImageResource(R.drawable.stu_check);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_ClassName;
        TextView item_ClassAddress;
        TextView item_ClassTime;
        TextView item_ClassTeacherName;
        TextView item_ClassDescription;
        TextView item_ClassGoalScore;
        TextView item_ClassHeadUp;
        ImageView item_ClassCheck;
        ViewHolder(View itemView) {
            super(itemView);
            item_ClassName = itemView.findViewById(R.id.item_ClassName);
            item_ClassAddress = itemView.findViewById(R.id.item_ClassAddress);
            item_ClassTime = itemView.findViewById(R.id.item_ClassTime);
            item_ClassTeacherName = itemView.findViewById(R.id.item_ClassTeacherName);
            item_ClassDescription = itemView.findViewById(R.id.item_ClassDescription);
            item_ClassGoalScore = itemView.findViewById(R.id.item_ClassGoalScore);
            item_ClassHeadUp = itemView.findViewById(R.id.item_ClassHeadUp);
            item_ClassCheck = itemView.findViewById(R.id.item_ClassCheck);
        }
    }
}