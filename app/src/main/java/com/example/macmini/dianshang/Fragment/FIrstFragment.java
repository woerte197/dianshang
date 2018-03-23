package com.example.macmini.dianshang.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.macmini.dianshang.Bottom.BindFragment;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.databinding.FragmentFirstBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FIrstFragment extends BindFragment<FragmentFirstBinding> {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showToolBar();
        setToolBarTitle("first");




    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_first;
    }

}
