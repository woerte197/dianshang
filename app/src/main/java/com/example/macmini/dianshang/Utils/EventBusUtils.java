package com.example.macmini.dianshang.Utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by macmini on 2018/5/30.
 */

public class EventBusUtils {
    private EventBus eventBus;
    public static EventBusUtils eventBusUtils = null;
    public EventMessage event=new EventMessage();
    public static EventBusUtils getIns() {
        if (eventBusUtils == null) {
            synchronized (EventBusUtils.class) {
                eventBusUtils = new EventBusUtils();
            }
        }
        return eventBusUtils;
    }

    public EventBusUtils() {
        eventBus = new EventBus();
    }

    public void register(Object o) {
        eventBus.register(o);
    }

    public void unregister(Object o) {
        eventBus.unregister(o);
    }
    public void PostMessage(int msgid, Object obj){
        event.setMessage(msgid);
        event.setObject(obj);
        eventBus.post(event);
    }
}
