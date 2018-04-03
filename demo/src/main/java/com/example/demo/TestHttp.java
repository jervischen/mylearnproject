package com.example.demo;

import com.example.demo.util.HttpClientUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-03-29 17:30.
 *
 * @author chenxiao
 */
public class TestHttp {
    private static Logger logger = LoggerFactory.getLogger(TestHttp.class);
    String url = "http://localhost:9100/";

    @Test
    public void testRedList(){
        url = url + "red/getList";

        String result = HttpClientUtil.doGet(url);
        System.out.println(result);
    }
}
