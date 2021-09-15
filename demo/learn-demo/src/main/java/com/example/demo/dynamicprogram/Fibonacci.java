package com.example.demo.dynamicprogram;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author Chen Xiao
 * 斐波那契数列
 * @since 2021-05-13 15:09
 */
public class Fibonacci {

    static int[] val = new int[10000];

    public static int fib(int n){
        if (val[n] != 0){
            return val[n];
        }


        if (n < 2){
            return n;
        }


        int res = fib(n - 1) + fib(n - 2);
        val[n] = res;
        return res;
    }

    public static void main(String[] args) {
        StopWatch stopWatch = StopWatch.create();
        stopWatch.start();
        int fib = Fibonacci.fib(100);
        System.out.println(fib);
        System.out.println(stopWatch.getTime());
    }
}
