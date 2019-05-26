package com.example.moshidemo.guanchazhe.core;

import java.lang.reflect.Method;


public class Event {

    //事件源
    private Object source;
    //目标方法
    private Object target;

    private Method callBack;

    private String trigger;

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    private long Time;

    public Event(Object target, Method callBack) {
        this.target = target;
        this.callBack = callBack;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public Event setTarget(Object target) {
        this.target = target;
        return this;
    }

    public Method getCallBack() {
        return callBack;
    }

    public Event setCallBack(Method callBack) {
        this.callBack = callBack;
        return this;
    }

    public String getTrigger() {
        return trigger;
    }

    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "\n\tsource=" + source +
                ",\n\t target=" + target +
                ", \n\tcallBack=" + callBack +
                ", \n\ttrigger='" + trigger + '\'' +
                '}';
    }
}
