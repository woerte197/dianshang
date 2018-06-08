package com.example.macmini.dianshang.JavaBean;

import com.example.macmini.dianshang.TypeFactory;
import com.example.macmini.dianshang.Watcher;

import java.util.List;

/**
 * Created by macmini on 2018/6/5.
 */

public class Country implements Watcher {
    private String name;
    private int nationid;
    private List<Areas> areas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNationid() {
        return nationid;
    }

    public void setNationid(int nationid) {
        this.nationid = nationid;
    }

    public List<Areas> getAreas() {
        return areas;
    }

    public void setAreas(List<Areas> areas) {
        this.areas = areas;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return 0;
    }
}
