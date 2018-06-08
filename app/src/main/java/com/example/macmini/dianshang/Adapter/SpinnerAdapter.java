package com.example.macmini.dianshang.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macmini.dianshang.R;

import java.util.List;
import java.util.zip.Inflater;


/**
 * Created by macmini on 2018/6/5.
 */

public class SpinnerAdapter extends BaseAdapter {
    private List<String> list;
    private LayoutInflater inflater;

    public SpinnerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void addd(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolderl;
        if (view == null) {
            viewHolderl = new ViewHolder();
            view = inflater.inflate(R.layout.layout_arease, null);
            viewHolderl.tvTitle = (TextView) view.findViewById(R.id.text5);
            view.setTag(viewHolderl);
        } else {
            viewHolderl = (ViewHolder) view.getTag();
        }
         viewHolderl.tvTitle.setText(list.get(i));
        return view;
    }
    class ViewHolder {
        TextView tvTitle;

    }
}
