package com.example.demo.redis;

import com.example.demo.io.TestFile;
import com.example.demo.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created in 2018-03-13 10:01.
 *
 * @author chenxiao
 */
public class TestPeachDockerRedis {
    private static Logger logger = LoggerFactory.getLogger(TestPeachDockerRedis.class);

    static Jedis jedis = null;

    static {
        //连接本地的 Redis 服务
        jedis = new Jedis("172.17.6.232", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    public static void main(String[] args) {
        TestPeachDockerRedis.getNjRank();
    }

    /**
     * 主播排行榜
     */
    public static void getNjRank() {
        String key = "LZ_LIVE_ACTIVITY_RANK_SORTED_SET_PEACH_";
        //获取昨天日期
        //   String date = DateUtil.formatDateToString(DateUtil.getYesterday(), DateUtil.date);
        String date = DateUtil.formatDateToString(new Date(), DateUtil.date);
        List<String> levelList = new ArrayList<>();
        levelList.add("_3");
        levelList.add("_2");
        levelList.add("_1");

        StringBuilder sb = new StringBuilder();

        for (String level : levelList) {
            String redisKey = key + date + level;
            if (level.equals("_3") ) {
                sb.append("桃花之神\r\n");
            } else if (level.equals("_2")) {
                sb.append("桃花仙子\r\n");
            } else {
                sb.append("桃花妖\r\n");
            }
            Set<String> njSet = jedis.zrevrangeByScore(redisKey, "+inf", "-inf", 0, 3);
            int count = 0;
            for (String nj : njSet) {
                count++;
                if (count == 1) {
                    sb.append("第一名主播：" + nj + "\r\n");
                } else if (count == 2) {
                    sb.append("第二名主播：" + nj + "\r\n");
                } else {
                    sb.append("第三名主播：" + nj + "\r\n");
                }
                String fanKey = "LZ_LIVE_ACTIVITY_FAN_SORTED_SET_PEACH_";
                Set<String> fanSet = jedis.zrevrangeByScore(fanKey + nj, "+inf", "-inf", 0, 3);
                for (String fan : fanSet) {
                    sb.append("粉丝：" + fan + "\r\n");
                }
                sb.append("\r\n");
            }
        }

        TestFile.write(sb, "D:/test.txt");
    }


}
