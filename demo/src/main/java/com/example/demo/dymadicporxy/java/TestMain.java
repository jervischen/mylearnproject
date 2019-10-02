package com.example.demo.dymadicporxy.java;

/**
 * @author Chen Xiao
 * @since 2019-10-02 16:14
 */
public class TestMain {

    public static void main(String[] args) {
        ProxySaler proxySaler=new ProxySaler();
        Person object= (Person) proxySaler.newInstall(new xiaoQiang("黄豪强","南山区"));
        object.buy1();
        object.buy();
    }
}
