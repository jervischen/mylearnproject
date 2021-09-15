package com.example.demo.dymadicporxy.java;

/**
 * @author Chen Xiao
 * @since 2019-10-02 16:15
 */
public class xiaoQiang implements Person{
    private String name;
    private String house;

    public xiaoQiang(String name, String house) {
        this.name = name;
        this.house = house;
    }

    @Override
    public void buy() {
        System.out.println(name+"买了"+house);
    }

    @Override
    public void buy1() {
        System.out.println("我是你爸爸");
    }
}
