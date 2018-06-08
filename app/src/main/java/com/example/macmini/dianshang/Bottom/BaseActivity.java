package com.example.macmini.dianshang.Bottom;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.macmini.dianshang.Observe;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.JavaBean.TabBean;
import com.example.macmini.dianshang.Utils.EventBusUtils;
import com.example.macmini.dianshang.Utils.EventMessage;
import com.example.macmini.dianshang.WechatPost;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements Observe {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        EventBusUtils.getIns().register(this);
        WechatPost.ins().register(this);
        WechatPost.ins().post();
    }

    public void addFragement(int id, BaseFragment fragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(id, fragment);
        transaction.commit();
    }

    public void showFragment(TabBean tabBean) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.show(tabBean.getFragment());
        transaction.commit();
    }

    public void hideFragment(TabBean tabBean) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.hide(tabBean.getFragment());
        transaction.commit();
    }

    public void setFragment(List<TabBean> list) {
        for (TabBean t : list) {
            if (t.isIsselected()) {
                showFragment(t);
            } else {
                hideFragment(t);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.getIns().unregister(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage event) {
        int msgId = event.getMessage();
        Object obj = event.getObject();
        upDateMsg(msgId, obj);
    }

    public void upDateMsg(int msgid, Object o) {

    }

    @Override
    public void update() {
        Log.i(TAG, "update: "+TAG);
    }
}
