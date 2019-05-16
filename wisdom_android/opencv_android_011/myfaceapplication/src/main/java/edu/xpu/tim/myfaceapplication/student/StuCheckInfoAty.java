package edu.xpu.tim.myfaceapplication.student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
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
    private MyRecyclerViewAdapter myAdapter;
    private RecyclerView stuAty_checkAtyRv;
    private List<AttendanceItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setContentView(R.layout.activity_stu_check_info_aty);

        //通过findViewById拿到RecyclerView实例
        stuAty_checkAtyRv = findViewById(R.id.stuAty_checkAtyRv);

        //设置RecyclerView管理器
        stuAty_checkAtyRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void getData() {
        AsyncHttpClient asyncHttpClient = new FinalAsyncHttpClient().getAsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("start", "1970-01-01 00:00:00");
        params.put("end", "2019-05-15 00:00:00");

        //准备数据包
        asyncHttpClient.post(AppConfig.classInfoAddress, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String classInfoStr = new String(responseBody);
                Log.i(AppConfig.TAG, classInfoStr);
                //展示获得的数据
                JSONObject jsonObject = JSONObject.parseObject(classInfoStr);
                JSONArray data = jsonObject.getJSONArray("data");
                for (int i = 0; i < data.size(); i++) {
                    AttendanceItem object = data.getObject(i, AttendanceItem.class);
                    list.add(object);
                    Log.i(AppConfig.TAG, object.toString());
                }
                //初始化适配器
                myAdapter = new MyRecyclerViewAdapter(list);

                //设置添加或删除item时的动画，这里使用默认动画
                stuAty_checkAtyRv.setItemAnimator(new DefaultItemAnimator());

                //设置适配器
                stuAty_checkAtyRv.setAdapter(myAdapter);

                //添加横线
                stuAty_checkAtyRv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                Log.i(AppConfig.TAG, data.toString());
                XToast.success(getContext(), "数据获取成功").show();

                myAdapter.setOnItemClickListener((view, position, data1) ->{
                    Intent intent = new Intent(getContext(), CheckSpecificInfoAty.class);

                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                XToast.error(getContext(), "请求网络数据失败：" + statusCode).show();
                finish();
            }
        });
    }
}


class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<AttendanceItem> list;
    private OnItemClickListener onItemClickListener;


    MyRecyclerViewAdapter(List<AttendanceItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stu_classinfo, parent, false);
        return new MyRecyclerViewAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.item_ClassName.setText(list.get(position).getName());
        holder.item_ClassAddress.setText(list.get(position).getTeacher_name() + " " + list.get(position).getClassroom_id());
        holder.item_ClassTime.setText(list.get(position).getTime());

        if(list.get(position).getCourse_note() == null ||
                list.get(position).getCourse_note().equals("")){
            holder.item_ClassDescription.setText("在古代其含义有两种：“博学”之态；与“小学”相对的“大人之学”。古代儿童八岁上小学，主要学习“洒扫、应对、进退、礼乐射御书数”之类的文化课和基本的礼节。");
        }else{
            holder.item_ClassDescription.setText(list.get(position).getCourse_note());
        }

        holder.item_ClassCheck.setImageResource("attend".equals(list.get(position).getStatus()) ? R.drawable.pass : R.drawable.nopass);

        //设置List监听事件
        int adapterPosition = holder.getAdapterPosition();
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new MyOnClickListener(position, list.get(adapterPosition)));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_ClassName;
        TextView item_ClassAddress;
        TextView item_ClassTime;
        TextView item_ClassDescription;
        ImageView item_ClassCheck;
        ViewHolder(View itemView) {
            super(itemView);
            item_ClassName = itemView.findViewById(R.id.item_ClassName);
            item_ClassAddress = itemView.findViewById(R.id.item_ClassAddress);
            item_ClassTime = itemView.findViewById(R.id.item_ClassTime);
            item_ClassDescription = itemView.findViewById(R.id.item_ClassDescription);
            item_ClassCheck = itemView.findViewById(R.id.item_ClassCheck);
        }
    }


    /**
     * 设置点击事件
     */
    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, AttendanceItem data);
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private AttendanceItem data;

        MyOnClickListener(int position, AttendanceItem data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, position, data);
        }
    }

}