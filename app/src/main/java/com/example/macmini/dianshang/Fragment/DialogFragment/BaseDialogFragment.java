package com.example.macmini.dianshang.Fragment.DialogFragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.Utils.EventBusUtils;
import com.example.macmini.dianshang.Utils.EventMessage;
import com.example.macmini.dianshang.databinding.FragmentBaseDialogBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.ThreadPoolExecutor;

import static com.example.macmini.dianshang.R.layout.fragment_base_dialog;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseDialogFragment<SV extends ViewDataBinding> extends DialogFragment {
    public SV bindview;
    public FragmentBaseDialogBinding baseDialogBinding;
    private RelativeLayout.LayoutParams cl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (this.isAdded()) {
            return;
        }
        super.show(manager, tag);
        EventBusUtils.getIns().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baseDialogBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base_dialog, null, false);
        bindview = DataBindingUtil.inflate(inflater, setlayout(), null, false);
        baseDialogBinding.frameLayout.addView(bindview.getRoot(),cl);
        baseDialogBinding.frameLayout.setBackgroundColor(Color.TRANSPARENT);
        return baseDialogBinding.getRoot();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        EventBusUtils.getIns().unregister(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage event) {
        int msgId = event.getMessage();
        Object obj = event.getObject();
        upDateMsg(msgId, obj);
    }

    protected void upDateMsg(int msgId, Object obj) {

    }

    protected abstract int setlayout();


}
