package com.cpu;

import java.util.ArrayList;

/**
 * @author Chen Xiao
 * @since 2021-09-27 19:47
 */
public class TestCpu {


    public static void main(String[] args) {

        new TestCpu().a();
    }

    public void a() {
        while (true) {
            b();
            c();
        }
    }


    public void b() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(1);
    }

    public void c() {
        byte[] bytes = new byte[1024 * 1024];
    }
}
