package edu.xpu.tim.myfaceapplication.fragment.teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.xpu.tim.myfaceapplication.R;

public class TFragmentHome extends Fragment {
    private static final String TAG = "TFragmentHome";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.teacher_fragment_home, container, false);
    }
}
