package com.example.macmini.dianshang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.TypeFactory;
import com.example.macmini.dianshang.TypeFactoryList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 2018/3/23.
 */

public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List list;
    private TypeFactoryList typeFactoryList;

    public BaseAdapter(Context context) {
        this.context = context;
        list = new ArrayList();
        typeFactoryList=new TypeFactoryList();
    }

    public void addData(List list) {
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, null, false);
        return typeFactoryList.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
