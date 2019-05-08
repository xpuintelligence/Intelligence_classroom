package edu.xpu.tim.myfaceapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import edu.xpu.tim.myfaceapplication.config.AppConfig;
import static android.view.KeyEvent.KEYCODE_BACK;

public class StudentAty extends AppCompatActivity {
    private static final String TAG = "StudentAty";

    private WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_aty);
        webView = findViewById(R.id.wv_stu_home);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://106.12.202.93/#/");
        //webView.loadUrl("https://www.baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KEYCODE_BACK)
            Log.i(AppConfig.TAG, "监听到退出按键");
        Log.i(TAG, webView.toString());
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            Log.i(AppConfig.TAG, "WEB返回键");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
