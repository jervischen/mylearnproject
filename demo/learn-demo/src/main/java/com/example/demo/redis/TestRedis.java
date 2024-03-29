package com.example.demo.redis;

import com.example.demo.bean.Menu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        jedis = new Jedis("pplive-6379-redis.lizhi.fm", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    static String key = "LZ_FANSLEVEL_NJ_FANS_MEDAL_NAME_INFO";
    static String field = "2636074689511825452";

    public static void main(String[] args) {
        delRedis();
    }

    public static void delRedis() {
        String key = "*5170857476530110591*";

        Set<String> keys = jedis.keys(key);
        for (String s : keys) {
            System.out.println(s);
           jedis.del(s);
        }
        jedis.close();
    }

    @Test
    public void a() {

        List<String> info = jedis.hmget("SC_ACT_WAKA_GAME_INFO", "5096622024198328447");
        System.out.println(info);
    }

    @Test
    public void b(){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");


        boolean flag = true;
        int i = 1;
        while (flag){
            System.out.println(list.size());
            if (list.size() == 1){
                flag =false;
            }
            list.remove("a");
        }

        Menu menu = new Menu();
        menu.getContent().setText("abc");
        System.out.println(menu);
    }
}
