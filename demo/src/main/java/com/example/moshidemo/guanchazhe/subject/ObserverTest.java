package com.example.moshidemo.guanchazhe.subject;


import com.example.moshidemo.guanchazhe.core.Event;

import java.lang.reflect.Method;

public class ObserverTest {

    public static void main(String[] args) throws NoSuchMethodException {
        Observer observer = new Observer();
        Method method = Observer.class.getMethod("advice", new Class<?>[]{
                Event.class});

        Subject subject = new Subject();
    //    subject.addListener(SubjectEventType.ADD,observer,method);

        subject.add();

    }
}
