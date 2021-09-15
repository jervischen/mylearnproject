package com.example.demo.testguice;

import com.google.common.base.Optional;

import java.math.BigDecimal;

/**
 * @author Chen Xiao
 * @since 2020-08-24 09:51
 */
public class TestGuice {

    public static void main(String[] args) {
        String or = Optional.fromNullable("11").or("22");
        System.out.println(or);

        String c = null;
        String a = Optional.fromNullable(c).or("22");

        System.out.println(a);



        BigDecimal b = new BigDecimal("52.999996");
        int f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
        System.out.println(f1);

    }
}
