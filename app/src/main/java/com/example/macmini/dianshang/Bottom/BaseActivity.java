package com.example.macmini.dianshang.Bottom;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.JavaBean.TabBean;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

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

    public  void setFragment(List<TabBean> list) {
        for (TabBean t : list) {
            if (t.isIsselected()) {
                showFragment(t);
            } else {
                hideFragment(t);
            }
        }

    }

}
