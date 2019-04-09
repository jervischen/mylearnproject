package com.example.demo.sevenbinfa.one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2019-01-28 15:08.
 *
 * @author chenxiao
 */
public class HelloWorld {
    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(){
            public void run(){
                System.out.println("hello from new thread");
            }
        };
        myThread.start();
        Thread.yield();
        System.out.println("hello from main thread");
        myThread.join();
    }
}
