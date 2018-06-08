package com.example.macmini.dianshang.JavaBean;

import com.example.macmini.dianshang.TypeFactory;
import com.example.macmini.dianshang.Watcher;

import java.util.List;

/**
 * Created by macmini on 2018/6/5.
 */

public class Areas implements Watcher {
 private String name;
 private int id;
 private List<City> city;

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

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
