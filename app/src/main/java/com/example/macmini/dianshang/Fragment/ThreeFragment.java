package com.example.macmini.dianshang.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.Bottom.BindFragment;
import com.example.macmini.dianshang.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BindFragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_three, container, false);

    }

    @Override
    protected int setlayout() {
        return 0;
    }

}