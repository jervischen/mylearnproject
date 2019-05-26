package com.example.moshidemo.guanchazhe.daili;

import com.example.moshidemo.guanchazhe.core.Event;
import com.example.moshidemo.guanchazhe.subject.Observer;
import com.example.moshidemo.guanchazhe.subject.Subject;
import com.example.moshidemo.guanchazhe.subject.SubjectEventType;

import java.lang.reflect.Method;

public class TestDL {
    public static void main(String[] args) throws NoSuchMethodException {

        Observer observer = new Observer();
        Method method = Observer.class.getMethod("advice", new Class<?>[]{
                Event.class});

        CglibProxy cglibProxy = new CglibProxy();
        Subject subject = cglibProxy.instance(Subject.class);
        subject.addListener(SubjectEventType.ADD,observer,method);

        subject.add();


    }
}
