package com.example.moshidemo.guanchazhe.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventListener {

    Map<Enum, Event> events = new HashMap<Enum, Event>();

    public void addListener(Enum eventType, Object target, Method callback) {
        //注册事件
        events.put(eventType,new Event(target,callback));
    }


    protected  void trigger(Enum call){
        if(!this.events.containsKey(call)){
            return ;
        }
        trgger(this.events.get(call).setTrigger(call.name()));
    }

    private void trgger(Event e) {
        e.setSource(this);
        e.setTime(System.currentTimeMillis());
        try {
            e.getCallBack().invoke(e.getTarget(),e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}