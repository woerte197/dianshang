package com.example.macmini.dianshang;


import com.example.macmini.dianshang.View.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 2018/6/5.
 */

public class WechatPost implements Observable {
    public static WechatPost wechatPost = null;

    public static WechatPost ins() {
        if (wechatPost == null) {
            synchronized (WechatPost.class) {
                wechatPost = new WechatPost();
            }
        }
        return wechatPost;
    }

    List<Observe> objects = new ArrayList<>();

    @Override
    public void register(Observe o) {
        if (!objects.contains(o)) {
            objects.add(o);
        }

    }

    @Override
    public void unregister(Observe o) {
        objects.remove(o);
    }

    @Override
    public void notifyo() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }
    }

    public void post() {
        notifyo();
    }
}
