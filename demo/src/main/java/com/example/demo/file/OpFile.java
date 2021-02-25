package com.example.demo.file;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created in 2019-04-10 12:56.
 *
 * @author chenxiao
 */
public class OpFile {
    private static Logger logger = LoggerFactory.getLogger(OpFile.class);

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();

        for (int i = 0; i < 96; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        System.out.println(list);
    }

}
