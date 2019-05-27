package com.example.demo.hdwindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created in 2019-05-27 10:43.
 *
 * @author chenxiao
 */
public class Loops {
    public static void dieLoop(Runnable runnable) {
        while (true) {
            run(runnable);
        }
    }

    public static void rateLoop(Runnable runnable, int mills) {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(mills);
            } catch (InterruptedException e) {

            }
            run(runnable);
        }
    }

    public static void fixLoop(Runnable runnable, int loop) {
        for (int i = 0; i < loop; i++) {
            run(runnable);
        }
    }

    private static void run(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
