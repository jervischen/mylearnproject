package com.example.demo;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;


/**
 * Created in 2018-01-22 11:46.
 *
 * @author chenxiao
 */
public class TestGuava {
    private static Logger logger = LoggerFactory.getLogger(TestGuava.class);


    /**
     * 谷歌LoadingCache缓存使用
     *
     * @param args
     */
    static LoadingCache<String, Optional<String>> cahceBuilder;


    @Before
    public void junitInit() {
        cahceBuilder = CacheBuilder.newBuilder().maximumSize(3)
                .build(new CacheLoader<String, Optional<String>>() {
                    @Override
                    public Optional<String> load(String key) throws Exception {
                        return init(key);
                    }
                });
    }

    public Optional<String> init(String key) throws Exception {
        Map<String, String> cacheMap = Maps.newHashMap();
        cacheMap.put("a", "a");
        cacheMap.put("b", "b");
        cacheMap.put("c", "c");

        if(key.equals("d")){
            cacheMap.put("d", "d");
        }

        String value = cacheMap.get(key);
        if (value != null) {
            return Optional.of(value);
        }
        return Optional.absent();
    }

    @Test
    public void testNull() throws Exception {

        System.out.println(cahceBuilder.get("a"));
        System.out.println(cahceBuilder.get("b"));
        System.out.println(cahceBuilder.get("c"));
        System.out.println(cahceBuilder.get("d"));

        System.out.println("=================");
        Set<String> set = cahceBuilder.asMap().keySet();
        for (String s : set){
            System.out.println(cahceBuilder.asMap().get(s));
        }
    }

}
