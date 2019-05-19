package edu.xpu.tim.myfaceapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;
import edu.xpu.tim.myfaceapplication.config.AppConfig;
import edu.xpu.tim.myfaceapplication.util.net.FinalAsyncHttpClient;
import edu.xpu.tim.myfaceapplication.util.net.GetPostUrl;

public class DetectionService extends Service {
    AsyncHttpClient client = new FinalAsyncHttpClient().getAsyncHttpClient();

    public DetectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Timer timer = new Timer();
        new Thread(()->{
            Map<String, String> map = new HashMap<>();
            map.put("id", "41604090109");
            GetPostUrl.post(AppConfig.nowSmg, map);
        }).start();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                final String[] ret = new String[1];
//                System.out.println("系统正在运行....");
//                client.post(AppConfig.classInfoAddress, null, new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                        ret[0] = new String(responseBody);
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                        ret[0] = new String(responseBody);
//                    }
//                });
//                Log.i(AppConfig.TAG, ret[0]);
//            }
//        }, new Date(), 2000);

        return super.onStartCommand(intent, flags, startId);
    }
}
