package com.example.demo;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
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
        jedis = new Jedis("172.17.6.232",6379);
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        float f = (float) 10/3;
        System.out.println(f);
        new BigDecimal(f).setScale(3, BigDecimal.ROUND_DOWN).floatValue();
        System.out.println(new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
        System.out.println(new BigDecimal(1.56).setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
        System.out.println(new BigDecimal(1.56).setScale(1, BigDecimal.ROUND_DOWN ).floatValue());
        System.out.println(new BigDecimal(1.56).setScale(1, BigDecimal.ROUND_FLOOR).floatValue());
        float a = 0.3f + 1;
        System.out.println(new BigDecimal(a).setScale(1, BigDecimal.ROUND_FLOOR).floatValue());
        System.out.println(new BigDecimal(a).setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue());
        System.out.println(new BigDecimal(a).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue());

        System.out.println(String.format("%.1f倍加速中", 188.566));
    }

    static  String key="LZ_FANSLEVEL_NJ_FANS_MEDAL_NAME_INFO";
    static  String field = "2636074689511825452";
    public static void main(String[] args) {
        System.out.println(jedis.hget(key,field));

        System.out.println(3<0.0);
        System.out.println(StringFilter("!@#$%^*&(<>aにほんご我是陈潇"));
    }

    public static String StringFilter(String medalName) {
        if (Strings.isNullOrEmpty(medalName)) {
            return null;
        }

        //勋章名字长度最长为3
        String newMedalName = "";
        String regEx = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$";
        Pattern p = Pattern.compile(regEx);
        for (char ch : medalName.toCharArray()) {
            Matcher m = p.matcher(String.valueOf(ch));
            if (m.matches()) {
                newMedalName += String.valueOf(ch);
            }

            if (newMedalName.length() == 3) {
                break;
            }
        }

        return newMedalName;
    }
}
