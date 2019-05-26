package com.example.demo.enumtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created in 2019-05-20 11:37.
 *
 * @author chenxiao
 */
public enum TimeType {
    MIMU(1, TimeUnit.MINUTES),
    SECON(2, TimeUnit.SECONDS),
    DAY(3, TimeUnit.DAYS),;

    private int value;
    private TimeUnit timeUnit;

    private static Map<Integer,TimeType> map = new HashMap<>();

    TimeType(int value, TimeUnit timeUnit) {
        this.value = value;
        this.timeUnit = timeUnit;
    }

    static {
        for (TimeType timeType : TimeType.values()) {
            map.put(timeType.getValue(),timeType);
        }
    }

    public int getValue() {
        return value;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public TimeType from(int key) {
        return map.get(key);
    }
}
