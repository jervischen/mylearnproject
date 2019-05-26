package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created in 2019-03-22 19:24.
 *
 * @author chenxiao
 */
public class TestF<S> {

    public static void main(String[] args) {
        Map map = new HashMap();
        System.out.println(map.get(null));
    }
}
