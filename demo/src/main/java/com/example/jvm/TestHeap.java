package com.example.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * @author Chen Xiao
 * @since 2020-10-18 12:55
 */
public class TestHeap {



    public static void main(String[] args) {
//        Byte[] a = new Byte[1024 * 1024 * 2];
//        Byte[] b = new Byte[1024 * 1024 * 2];
//        Byte[] c = new Byte[1024 * 1024 * 2];
//        Byte[] d = new Byte[1024 * 1024 * 4];

        for (int a= 0;a < 100; a++){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i=0;i< 170;i++){
                        byte[] b=new byte[64];
                    }
                }
            }).start();
        }

    }
}
