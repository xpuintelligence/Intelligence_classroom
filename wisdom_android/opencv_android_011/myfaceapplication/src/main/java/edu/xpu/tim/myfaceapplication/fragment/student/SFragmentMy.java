package edu.xpu.tim.myfaceapplication.fragment.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import edu.xpu.tim.myfaceapplication.R;

public class SFragmentMy extends Fragment {
    private static final String TAG = "SFragmentMy";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.student_fragment_my, container, false);
        System.out.println("SFragmentMy "+ inflater);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentActivity activity = getActivity();
        assert activity != null;
        TextView viewById = activity.findViewById(R.id.stuInfo);
        SharedPreferences first = activity.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String retStr = first.getString("retStr", "");
        Log.i(TAG, retStr);
        try{
            JSONObject jsonObject = JSONObject.parseObject(retStr);
            JSONObject data = jsonObject.getJSONObject("data");
            Log.i(TAG, data.toJSONString());
            viewById.setText(String.format("%s %s", data.getString("name"), data.getString("id")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
