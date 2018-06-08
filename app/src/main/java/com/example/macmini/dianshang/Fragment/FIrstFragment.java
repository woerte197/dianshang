package com.example.macmini.dianshang.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.macmini.dianshang.Adapter.SpinnerAdapter;
import com.example.macmini.dianshang.Activity.UserActivity;
import com.example.macmini.dianshang.Bottom.BindFragment;
import com.example.macmini.dianshang.Fragment.DialogFragment.UpdateFragment;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FIrstFragment extends BindFragment<FragmentFirstBinding> {

    private static final String TAG = "FIrstFragment";
    private SpinnerAdapter spinnerAdapter;

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
            UpdateFragment updateFragment = new UpdateFragment();
            updateFragment.show(getActivity().getSupportFragmentManager(), "update");
        });
        bindView.imageUser.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), UserActivity.class);
            getActivity().startActivity(intent);
        });
        List<String> list = new ArrayList<>();
        list.add("大连");
        list.add("沈阳");
        list.add("沈阳1");
        list.add("沈阳2");
        list.add("沈阳3");
        list.add("沈阳4");
        list.add("沈阳5");
        spinnerAdapter = new SpinnerAdapter(getActivity());
        spinnerAdapter.addd(list);
        bindView.spinner.setAdapter(spinnerAdapter);
        bindView.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "onItemSelected: " + list.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    protected int setlayout() {
        return R.layout.fragment_first;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
