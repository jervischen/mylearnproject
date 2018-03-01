package com.example.demo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created in 2018-01-11 12:00.
 *
 * @author chenxiao
 */
public class RedisJunit {

    static Jedis jedis = null;

    @BeforeClass
    public static void runOnceBeforeClass() {
        //连接本地的 Redis 服务
        jedis = new Jedis("172.17.6.232", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    @After
    public void runOnceAfterClass() {
        jedis.close();
        System.out.println("关闭jedis连接");
    }


    @Test
    public void delKeys() {
        //      Set<String> keys = jedis.keys("LZ_FANSLEVEL_NJ_FANS_MEDAL*");
//        Set<String> keys = jedis.keys("LZ_FANSLEVEL_USER_*");
//        Set<String> keys = jedis.keys("LZ_FANSLEVEL_USER_*2640877571050324524");
        Set<String> keys = jedis.keys("LZ_FANSLEVEL_NJ_FANS_MEDAL_NAME_INFO_*2640877571050324524");
//        Set<String> keys = jedis.keys("LZ_FANSLEVEL_USER_*2646620839973229612");
        // Set<String> keys = jedis.keys("chenx*");

        System.out.println(keys);
        Pipeline pl = jedis.pipelined();
        for (String key : keys) {
            pl.del(key);
        }
        pl.sync();
        System.out.println("删除成功");
    }

    @Test
    public void hdel() {
       String key = "LZ_FANSLEVEL_NJ_FANS_MEDAL_NAME_INFO";
        Long hdel = jedis.hdel(key, "2640877571050324524");
        System.out.println("删除成功" + hdel);
    }

    @Test
    public void getValue() {
        Set<String> keys = jedis.keys("LZ_FANSLEVEL_USER_*2646620839973229612");
        System.out.println(keys);
        for (String key : keys) {
            Map<String, String> map = jedis.hgetAll(key);

            System.out.println(map);
        }
    }

    @Test
    public void exist() {
       String key = "LZ_FANSLEVEL_USER_FANS_MEDAL_2646620839973229612";
        System.out.println(jedis.exists(key));
    }

    @Test
    public void hkeys(){
        String key = "LZ_FANSLEVEL_NJ_FANS_MEDAL_LEVEL_2118074";
        Set<String> hkeys = jedis.hkeys(key);
        System.out.println(hkeys);
    }

    @Test
    public void hmse(){
        String key="cxtest";
        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        jedis.hmset(key,map);
    }

}
