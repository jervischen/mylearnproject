package com.example.demo.redis;

import com.example.demo.bean.Menu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;

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
        jedis = new Jedis("172.17.6.233", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    static String key = "LZ_FANSLEVEL_NJ_FANS_MEDAL_NAME_INFO";
    static String field = "2636074689511825452";

    public static void main(String[] args) {
        delRedis();
    }

    public static void delRedis() {
        String key = "SC_ACT_WAKA_*";

        ScanParams scanParams = new ScanParams();
        scanParams.match(key);
        scanParams.count(20000000);
        ScanResult<String> scan = jedis.scan(1000000, scanParams);
        for (String s : scan.getResult()) {
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
