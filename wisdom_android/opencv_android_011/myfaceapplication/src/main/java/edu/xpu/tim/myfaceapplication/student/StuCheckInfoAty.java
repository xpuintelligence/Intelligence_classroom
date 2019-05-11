package edu.xpu.tim.myfaceapplication.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xuexiang.xui.widget.toast.XToast;

import cz.msebera.android.httpclient.Header;
import edu.xpu.tim.myfaceapplication.R;
import edu.xpu.tim.myfaceapplication.config.AppConfig;
import edu.xpu.tim.myfaceapplication.util.net.FinalAsyncHttpClient;

import static com.xuexiang.xui.XUI.getContext;

public class StuCheckInfoAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_check_info_aty);

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
