package com.example.macmini.dianshang.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.Bottom.BindFragment;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.databinding.FragmentThreeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BindFragment<FragmentThreeBinding> {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_three;
    }

}
