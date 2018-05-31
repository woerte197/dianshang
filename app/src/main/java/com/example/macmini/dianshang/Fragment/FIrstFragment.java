package com.example.macmini.dianshang.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.example.macmini.dianshang.ApiFactory.ApiFactory;
import com.example.macmini.dianshang.Bottom.BindFragment;
import com.example.macmini.dianshang.Fragment.DialogFragment.UpdateFragment;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.Tool.ShowMessege;
import com.example.macmini.dianshang.databinding.FragmentFirstBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class FIrstFragment extends BindFragment<FragmentFirstBinding> {

    private static final String TAG = "FIrstFragment";


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showToolBar();
        setToolBarTitle("首页");

        initEvent();
        initData();

    }




    private void initData() {
    }

    private void initEvent() {
        bindView.buttonDown.setOnClickListener(view -> {
            UpdateFragment updateFragment=new UpdateFragment();
            updateFragment.show(getActivity().getSupportFragmentManager(),"update");


        });
    }



    @Override
    protected int setlayout() {
        return R.layout.fragment_first;
    }

}
