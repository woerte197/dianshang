package com.example.macmini.dianshang.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.example.macmini.dianshang.Bottom.BaseActivity;
import com.example.macmini.dianshang.Fragment.FIrstFragment;
import com.example.macmini.dianshang.Fragment.SecondFragment;
import com.example.macmini.dianshang.Fragment.ThreeFragment;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.JavaBean.TabBean;
import com.example.macmini.dianshang.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    private List<TabBean> tabBeanList;
    private TabBean tabBean = new TabBean("first", new FIrstFragment(), true, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);
    private TabBean tabBean2 = new TabBean("second", new SecondFragment(), false, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);
    private TabBean tabBean3 = new TabBean("three", new ThreeFragment(), false, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initFragement();
        isGrantExternalRW();
        binding.linearFirst.setOnClickListener(this);
        binding.linearSecond.setOnClickListener(this);
        binding.linearThree.setOnClickListener(this);

    }


    private void initFragement() {
        tabBeanList = new ArrayList<>();
        tabBeanList.add(tabBean);
        tabBeanList.add(tabBean2);
        tabBeanList.add(tabBean3);
        binding.setTab(tabBeanList);
        for (TabBean tabBean:tabBeanList) {
            addFragement(R.id.frame_layout_main,tabBean.getFragment());
        }
        setFragment(tabBeanList);
    }
    public void isGrantExternalRW() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_first:
                tabBean.setIsselected(true);
                tabBean2.setIsselected(false);
                tabBean3.setIsselected(false);
                setFragment(tabBeanList);
                break;
            case R.id.linear_second:
                tabBean.setIsselected(false);
                tabBean3.setIsselected(false);
                tabBean2.setIsselected(true);
                setFragment(tabBeanList);
                break;
            case R.id.linear_three:
                tabBean3.setIsselected(true);
                tabBean.setIsselected(false);
                tabBean2.setIsselected(false);
                setFragment(tabBeanList);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: "+requestCode);
    }
}
