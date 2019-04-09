package com.example.demo.redis;

import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created in 2018-01-08 18:36.
 *
 * @author chenxiao
 */
public class TestRedis {
    private static Logger logger = LoggerFactory.getLogger(TestRedis.class);

    static Jedis jedis = null;

    static {
        //连接本地的 Redis 服务
        jedis = new Jedis("172.17.6.232", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    static String key = "LZ_FANSLEVEL_NJ_FANS_MEDAL_NAME_INFO";
    static String field = "2636074689511825452";

    public static void main(String[] args) {
        delRedis();
    }

    public static void delRedis() {
        String key = "LZ_RED_ENVELOPE_QUERY_AVAILABLE_RED_ENVELOPE_SORT_SET_*";

        Set<String> keys = jedis.keys(key);
        for (String s : keys) {
            jedis.del(s);
        }
        jedis.close();
    }

    @Test
    public void a() {

        Double a = null;
        if(a > 1){
            System.out.println(55);
        }
    }
}
