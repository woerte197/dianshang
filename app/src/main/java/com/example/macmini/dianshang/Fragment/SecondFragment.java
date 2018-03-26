package com.example.macmini.dianshang.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.Bottom.BindFragment;
import com.example.macmini.dianshang.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BindFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_second;
    }

}
