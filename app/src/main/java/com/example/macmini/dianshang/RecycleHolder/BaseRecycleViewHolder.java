package com.example.macmini.dianshang.RecycleHolder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.macmini.dianshang.Adapter.BaseAdapter;
import com.google.gson.internal.bind.DateTypeAdapter;

/**
 * Created by macmini on 2018/3/26.
 */

public abstract class BaseRecycleViewHolder<T, SV extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public  SV bindview;
    public  Context context;
    public  LayoutInflater layoutInflater;
    public BaseRecycleViewHolder(View itemView) {
        super(itemView);
        bindview= DataBindingUtil.bind(itemView);
        bindview.executePendingBindings();
        context=itemView.getContext();
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public abstract void setUpView(T model, int position, BaseAdapter adapter);
    public SV getBindview(){
        return bindview;
    }
    public  void setKeyWords(String keyWords){}
}
