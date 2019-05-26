package com.example.moshidemo.guanchazhe.daili;

import com.example.moshidemo.guanchazhe.core.EventListener;
import com.example.moshidemo.guanchazhe.subject.SubjectEventType;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {


    private Method callBack;
    private Object observer;

    public <T> T instance(Class<T> clz,Object observer, Method callBack) {
        this.observer = observer;
        this.callBack = callBack;

        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(clz);
        // 设置enhancer的回调对象
        enhancer.setCallback(this);
        // 创建代理对象
        return (T)enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        EventListener listener = new EventListener();
        if (method.getName().equalsIgnoreCase(SubjectEventType.ADD.name())){
            listener.addListener(SubjectEventType.ADD,observer,callBack);
            listener.trigger(SubjectEventType.ADD);
        }

 //       methodProxy.invokeSuper(obj,objects);
        return null;
    }
}
