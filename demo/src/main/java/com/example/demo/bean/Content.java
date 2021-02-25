package com.example.demo.bean;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.DateUtil;
import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * Created in 2018-02-06 10:41.
 *
 * @author chenxiao
 */
@Data
//@Builder
//@AllArgsConstructor
public class Content {
    /**
     * 红包id
     */
    private long id;

   // private long belongId;

    private String text;


    public static void main(String[] args) {

        String a = new String("234");


        Runnable r = new Runnable(){
            @Override
            public void run() {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();


                System.out.println(1);


                LockSupport.parkNanos(1000 * 1000 * 1000 * 2);

                System.out.println(2);

                stopWatch.stop();

                System.out.println(stopWatch.getTime());
            }
        };

        new Thread(r).start();
        System.out.println(3);

    }

    @Test
    public void test(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        long timeDown = new DateTime().withMillisOfDay(1).getMillis() - System.currentTimeMillis();
        System.out.println(timeDown);

        String beginTime = "2020-12-11 20:00:00";
        DateTime beginDate = DateTime.parse(beginTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        int i = beginDate.minuteOfDay().get();
        System.out.println(i);
        System.out.println(beginDate.getMillis());

//        int minute = Calendar.getInstance().get(Calendar.MINUTE);
//        System.out.println(minute);
//
//
//        Date elimStartTime = DateUtil.parseSimple("2020-12-11 20:00:00");
//        System.out.println(elimStartTime.getHours());
//        System.out.println(elimStartTime.getMinutes());
//        System.out.println(new Date(System.currentTimeMillis()).getMinutes());

        System.out.println(!ArrayUtils.contains(new Object[]{}, "json"));

    }

    @Test
    public void tesat(){
       /* String replace = "[7,]".replace("[", "").replace("]", "");
        String[] split = replace.split(",");
        System.out.println(split[0]);
        System.out.println(split[1]);

        List<String> strings = Splitter.on(",").trimResults().splitToList(replace);

        System.out.println(strings.get(0));
        System.out.println(strings.get(1));*/

        Date date = DateUtil.parseSimple("2020-12-16 11:00");
        System.out.println(date);

    }
}
