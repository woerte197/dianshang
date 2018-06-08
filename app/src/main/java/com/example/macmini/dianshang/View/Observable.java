package com.example.macmini.dianshang.View;

import com.example.macmini.dianshang.Observe;

/**
 * Created by macmini on 2018/6/5.
 */

public interface Observable {
    void register(Observe o);
    void unregister(Observe o);
    void notifyo();
}
