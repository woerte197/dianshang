package com.example.macmini.dianshang;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.macmini.dianshang.JavaBean.User;

/**
 * Created by macmini on 2018/3/23.
 */

public class TypeFactoryList implements TypeFactory {
    @Override
    public int type(User user) {
        return 0;
    }

    public RecyclerView.ViewHolder createViewHolder(int viewType, View itemView) {
        switch (viewType){
            case 1:
                return null;

                default:
                    return null;
        }
    }
}
