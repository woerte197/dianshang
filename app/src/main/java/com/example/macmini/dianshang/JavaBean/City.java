package com.example.macmini.dianshang.JavaBean;

import com.example.macmini.dianshang.TypeFactory;
import com.example.macmini.dianshang.Watcher;

/**
 * Created by macmini on 2018/6/5.
 */

public class City implements Watcher {
    private String name;
    private int id;

    private City(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    public static class Builder {
        private String name;
        private int id;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }
}
