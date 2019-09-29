package com.example.demo;

import com.example.demo.util.HttpClientUtil;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created in 2018-03-29 17:30.
 *
 * @author chenxiao
 */
public class TestHttp {
    private static Logger logger = LoggerFactory.getLogger(TestHttp.class);
    String url = "http://localhost:9100/";

    @Test
    public void testRedList() {
        url = url + "red/getList";

        String result = HttpClientUtil.doGet(url);
        System.out.println(result);
    }

    @Test
    public void testSendMsg() {
//        String url = "http://intapi.253.com/send/json";
//        String url = "http://intapi.253.com/send";
        String url = "http://intapi.253.com/mt"; //不行

        Map<String, Object> param = new HashMap<>();
        param.put("account", "I7256627");
        param.put("password", "i73ONfDlcnfdf1");
        param.put("msg", "您的验证码是1234");
        param.put("mobile", "8613798959615");
        HttpClientUtil.doPostJson(url, param);
    }


    @Test
    public void testSendRongCloud() {
        //   String url = "http://172.17.36.93:7902/rongcloud/receive";

        String url = "http://localhost:7902/rongcloud/receive";

        Map<String, Object> param = new HashMap<>();
        param.put("fromUserId", "999");
        param.put("toUserId", "99");
        param.put("objectName", "RC:TxtMsg");
        param.put("content", "{\"content\":\"Hello world!\",\"extra\":\"\"}");
        param.put("channelType", "PERSON");
        param.put("msgTimestamp", System.currentTimeMillis());
        param.put("msgUID", "8888999");
        param.put("sensitiveType", "0");
        param.put("source", "iOS");
        param.put("groupUserIds", "");
        HttpClientUtil.doPost(url, param);
    }

    @Test
    public void a() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(100);
        }
        for (int i = 0; i < 30; i++) {
            list.add(200);
        }
        for (int i = 0; i < 19; i++) {
            list.add(500);
        }
        list.add(3000);

        System.out.println(Lists.reverse(list));
        Collections.shuffle(list);
        System.out.println(list);
        System.out.println(list.size());

        System.out.println("123|456".contains("|"));
    }
}
