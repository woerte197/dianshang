package com.example.macmini.dianshang.Bottom;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.databinding.FragmentBindBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BindFragment<SV extends ViewDataBinding> extends BaseFragment {
    FragmentBindBinding binding;
    protected SV bindView;
    private RelativeLayout.LayoutParams cl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bind, null, false);
        bindView = DataBindingUtil.inflate(inflater, setlayout(), null, false);
        binding.relativeBind.addView(bindView.getRoot(), cl);
        setHasOptionsMenu(true);
        setToolBarTitle("");
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    protected abstract int setlayout();

    protected void showToolBar() {
        binding.toolbarBind.setVisibility(View.VISIBLE);
    }

    protected void hideToolBar() {
        binding.toolbarBind.setVisibility(View.GONE);
    }

    protected void setToolBarTitle(String s) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbarBind);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        binding.textToolbarBind.setText(s);
    }
}
