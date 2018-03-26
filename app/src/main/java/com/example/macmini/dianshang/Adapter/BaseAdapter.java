package com.example.macmini.dianshang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.RecycleHolder.BaseRecycleViewHolder;
import com.example.macmini.dianshang.TypeFactory;
import com.example.macmini.dianshang.TypeFactoryList;
import com.example.macmini.dianshang.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 2018/3/23.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseRecycleViewHolder> {
    private Context context;
    private List<Watcher> list;
    private TypeFactoryList typeFactoryList;

    public BaseAdapter(Context context) {
        this.context = context;
        list = new ArrayList();
        typeFactoryList = new TypeFactoryList();
    }

    public void addData(List<Watcher> list) {
        this.list = list;

    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, null, false);
        return typeFactoryList.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        //   holder.setKeyWords(this.);
        try {
            holder.setUpView(list.get(position), position, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemViewType(int position) {
        try {
            return list.get(position).type(typeFactoryList);
        } catch (Exception e) {
            return super.getItemViewType(position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
