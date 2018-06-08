package com.example.macmini.dianshang.JavaBean;

import com.example.macmini.dianshang.Persent.Builder;

public  class BuilderRelaize implements Builder {
    User user = new User();

    @Override
    public BuilderRelaize username(String username) {
        user.setUsername(username);
        return this;
    }

    @Override
    public BuilderRelaize password(String password) {
        user.setPassword(password);
        return this;
    }

    public User builde() {
        return user;
    }
}
