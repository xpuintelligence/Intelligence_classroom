package edu.xpu.tim.myfaceapplication.fragment.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.xpu.tim.myfaceapplication.R;


public class SFragmentMain extends Fragment {
    private static final String TAG = "SFragmentMain";
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inflate =  LayoutInflater.from(getActivity()).
                inflate(R.layout.student_fragment_main, container, false);

        return inflate;
    }

    @Override
    public void onStart() {
        FragmentActivity activity = getActivity();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
