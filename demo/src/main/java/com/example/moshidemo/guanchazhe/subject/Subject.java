package com.example.moshidemo.guanchazhe.subject;


import com.example.moshidemo.guanchazhe.core.EventListener;

public class Subject {

    public void add(){
        //trigger(SubjectEventType.ADD);
        System.out.println("新增方法");
    }

    public void delete(){
        //trigger(SubjectEventType.DELETE);
        System.out.println("删除方法");
    }
}
