package com.example.demo.guava;

import lombok.Data;

/**
 * @author Chen Xiao
 * @since 2019-12-24 18:12
 */

public class Result<T> {

    T a;

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }
}
