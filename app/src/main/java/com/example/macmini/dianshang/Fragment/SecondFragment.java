package com.example.macmini.dianshang.Fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.Activity.TestActivity;
import com.example.macmini.dianshang.Bottom.BindFragment;
import com.example.macmini.dianshang.JavaBean.City;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.databinding.FragmentSecondBinding;
import com.example.macmini.dianshang.databinding.LayoutBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BindFragment<FragmentSecondBinding> {
    private static final String TAG = "SecondFragment";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = View.inflate(getActivity(), R.layout.layout, null);
        LayoutBinding binding = DataBindingUtil.bind(view);
        binding.text1.setText("nihao");
        binding.text2.setText("你好啊");
        binding.setP(() -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), TestActivity.class);
            getActivity().startActivity(intent);
        });
        bindView.viewliper.addView(view);
        City.Builder builder = new City.Builder();
        City city = builder.id(1).name("王洋").build();
        Log.i(TAG, "onActivityCreated: "+city.getName());
    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_second;
    }

}
