package com.example.macmini.dianshang.JavaBean;

import com.example.macmini.dianshang.TypeFactory;
import com.example.macmini.dianshang.Watcher;

/**
 * Created by macmini on 2018/3/23.
 */

public class User implements Watcher {
    private String name;
    private String pass;


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
