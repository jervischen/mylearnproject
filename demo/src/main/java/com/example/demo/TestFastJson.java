package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created in 2018-01-30 11:01.
 *
 * @author chenxiao
 */
public class TestFastJson {
    private static Logger logger = LoggerFactory.getLogger(TestFastJson.class);

    @Test
    public void testMapToJson(){
        Map<String,Object> content = new HashMap<>();
        content.put("id",123);
        content.put("a","a");
        content.put("b","b");

        System.out.println(JSON.toJSON(content).toString());
    }
}
