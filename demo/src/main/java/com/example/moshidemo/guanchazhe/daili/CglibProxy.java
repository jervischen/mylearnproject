package com.example.moshidemo.guanchazhe.daili;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {


    public <T> T instance(Class<T> clz) {
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

        methodProxy.invokeSuper(obj,objects);
        return null;
    }
}
