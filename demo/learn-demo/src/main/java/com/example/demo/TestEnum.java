package com.example.demo;

import java.util.Date;

/**
 * Created in 2018-03-07 15:36.
 *
 * @author chenxiao
 */
public enum TestEnum {

    PRIMARY(1, new Date()), MIDD(2, new Date());
    private int level;
    private Date name;

    private TestEnum(int level, Date name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public Date getName() {
        return name;
    }

    public static void main(String[] args)throws Exception {
        int i = 0;
        while (i < 10) {
            Thread.sleep(1000);
            System.out.println(TestEnum.PRIMARY.getName());
        }
    }
}
