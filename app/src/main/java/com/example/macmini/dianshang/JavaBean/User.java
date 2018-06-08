package com.example.macmini.dianshang.JavaBean;

import com.example.macmini.dianshang.TypeFactory;
import com.example.macmini.dianshang.Watcher;

/**
 * Created by macmini on 2018/3/23.
 */

public class User implements Watcher {
    private String username;
    BuilderRelaize builderRelaize;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
