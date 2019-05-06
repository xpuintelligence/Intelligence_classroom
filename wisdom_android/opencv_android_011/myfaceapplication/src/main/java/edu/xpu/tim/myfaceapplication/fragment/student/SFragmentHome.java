package edu.xpu.tim.myfaceapplication.fragment.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.xpu.tim.myfaceapplication.R;

public class SFragmentHome extends Fragment {
    private View inflate;
    private static final String TAG = "SFragmentHome";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate =  LayoutInflater.from(getActivity()).
                inflate(R.layout.student_fragment_home, container, false);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
