package com.example.demo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * Created in 2018-08-11 15:55.
 *
 * @author chenxiao
 */
public class TestJVM {
    public static void main(String[] args) {
        String str = "88,99";
        String[] split = str.split(",");
        System.out.println(split.length);

        List<String> split1= Splitter.on(",").splitToList(str);
        System.out.println(split1.contains(String.valueOf(88)));
        System.out.println(split1);


        String join = Joiner.on("_").join("a", "b");
        System.out.println(join);


        Runnable runnable = () -> System.out.println(111);

        runnable.run();
    }
}
