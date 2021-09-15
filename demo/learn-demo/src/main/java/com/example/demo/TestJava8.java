package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created in 2018-01-08 14:39.
 *
 * @author chenxiao
 */
public class TestJava8 {
    private static Logger logger = LoggerFactory.getLogger(TestJava8.class);


    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();

        for (int i=0;i<1000;i++) {
            System.out.println("第"+i+"次");
            list.add(new Byte[1*1024*1024]);
        }
        System.out.println("创建完毕！");
    }
}
