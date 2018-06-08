package com.example.macmini.dianshang.RecycleHolder;

import android.view.View;

import com.example.macmini.dianshang.Adapter.BaseAdapter;
import com.example.macmini.dianshang.JavaBean.City;
import com.example.macmini.dianshang.databinding.LayoutCityBinding;

/**
 * Created by macmini on 2018/6/5.
 */

public class LayoutCityHolder extends BaseRecycleViewHolder<City, LayoutCityBinding> {
    public LayoutCityHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void setUpView(City model, int position, BaseAdapter adapter) {
        bindview.setCity(model);
    }
}
