package com.example.macmini.dianshang.Activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.macmini.dianshang.Adapter.BaseAdapter;
import com.example.macmini.dianshang.Bottom.BaseActivity;
import com.example.macmini.dianshang.JavaBean.Areas;
import com.example.macmini.dianshang.JavaBean.City;
import com.example.macmini.dianshang.JavaBean.Country;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.Utils.AssestsUtils;
import com.example.macmini.dianshang.Watcher;
import com.example.macmini.dianshang.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;

import static com.example.macmini.dianshang.Utils.AssestsUtils.*;

public class TestActivity extends BaseActivity {
    ActivityTestBinding binding;
    BaseAdapter adapter,adaptera;
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        Country country = AssestsUtils.ins().getCountry();
        adapter = new BaseAdapter(this);
        binding.recycle1.setLayoutManager(new LinearLayoutManager(this));
        binding.recycle1.setAdapter(adapter);
        List<Areas> areas = country.getAreas();
        List<Watcher> list = new ArrayList<>();
        for (Areas a : areas) {
            list.add(a);
        }
        Log.i(TAG, "onCreate: " + list);
        adapter.addData(list);
    }

    @Override
    public void upDateMsg(int msgid, Object o) {
        super.upDateMsg(msgid, o);
        switch (msgid) {
            case 222:
                Areas areas = (Areas) o;
                initCity(areas);
                Log.i(TAG, "upDateMsg: " + areas);
                break;
        }
    }

    private void initCity(Areas areas) {
        List<Watcher> watcherList = new ArrayList<>();
        watcherList.clear();
        adaptera = new BaseAdapter(this);
        binding.recycle2.setAdapter(adaptera);
        binding.recycle2.setLayoutManager(new LinearLayoutManager(this));
        for (City c : areas.getCity()) {
            watcherList.add(c);
        }
        adaptera.addData(watcherList);
    }
}
