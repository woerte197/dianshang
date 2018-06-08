package com.example.macmini.dianshang.RecycleHolder;

import android.view.View;

import com.example.macmini.dianshang.Adapter.BaseAdapter;
import com.example.macmini.dianshang.JavaBean.Areas;
import com.example.macmini.dianshang.Utils.EventBusUtils;
import com.example.macmini.dianshang.databinding.LayoutAreaseBinding;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by macmini on 2018/6/5.
 */

public class LayoutAreaseHolder extends BaseRecycleViewHolder<Areas,LayoutAreaseBinding> {

    public LayoutAreaseHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(Areas model, int position, BaseAdapter adapter) {
        bindview.setArease(model);
        bindview.setP(w -> {
            Areas areas=(Areas)w;
            EventBusUtils.getIns().PostMessage(222,areas);
        });
    }
}
