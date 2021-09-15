package com.javap;

/**
 * @author Chen Xiao
 * @since 2021-09-10 22:40
 */
public class Test {
    private int num1 = 1;
    public static int NUM1 = 100;

    public static String OH="123234fghghh";
    public int func(int a,int b){
        return add(a,b);
    }
    public int add(int a,int b) {
        return a+b+num1;
    }
    public int sub(int a, int b) {
        return a-b-NUM1;
    }

    public String addStr(String a){
        return OH + a;
    }

    public static void main(String[] args) {
        System.out.println(1+2);
    }
}
