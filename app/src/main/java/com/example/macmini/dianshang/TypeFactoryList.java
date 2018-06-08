package com.example.macmini.dianshang;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.macmini.dianshang.JavaBean.Areas;
import com.example.macmini.dianshang.JavaBean.City;
import com.example.macmini.dianshang.JavaBean.Country;
import com.example.macmini.dianshang.JavaBean.User;
import com.example.macmini.dianshang.RecycleHolder.BaseRecycleViewHolder;
import com.example.macmini.dianshang.RecycleHolder.LayoutAreaseHolder;
import com.example.macmini.dianshang.RecycleHolder.LayoutCityHolder;

/**
 * Created by macmini on 2018/3/23.
 */

public class TypeFactoryList implements TypeFactory {
    private static final int LAYOPUT_AREASE = R.layout.layout_arease;
    private static final int LAYPOUT_CITY = R.layout.layout_city;

    @Override
    public int type(User user) {
        return 0;
    }

    @Override
    public int type(Country country) {
        return 0;
    }

    @Override
    public int type(City city) {
        return LAYPOUT_CITY;
    }

    @Override
    public int type(Areas areas) {
        return LAYOPUT_AREASE;
    }

    public BaseRecycleViewHolder createViewHolder(int viewType, View itemView) {
        switch (viewType) {
            case LAYOPUT_AREASE:
                return new LayoutAreaseHolder(itemView);
            case LAYPOUT_CITY:
                return new LayoutCityHolder(itemView);
            default:
                return null;
        }
    }
}
