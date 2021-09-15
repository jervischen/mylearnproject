package com.example.moshidemo.guanchazhe.subject;


import com.example.moshidemo.guanchazhe.core.Event;

public class Observer {

    public void advice(Event event){
        System.out.println("=======触发事件，打印方法============" + event.toString());
    }
}
