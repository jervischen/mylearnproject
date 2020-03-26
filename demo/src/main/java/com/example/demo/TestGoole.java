package com.example.demo;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.collections.Maps;

/**
 * Created in 2018-11-01 17:40.
 *
 * @author chenxiao
 */
public class TestGoole {
    private static Logger logger = LoggerFactory.getLogger(TestGoole.class);

    public static void main(String[] args) {
        String join = Joiner.on("_").join("a", "b");
        System.out.println(join);

        Maps.newHashMap();
    }
}
