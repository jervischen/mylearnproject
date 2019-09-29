package com.example.demo;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


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


    static LoadingCache<String, String> cahceBuilder1;


    @Before
    public void junitInit() {
        cahceBuilder = CacheBuilder.newBuilder().maximumSize(3)
                .build(new CacheLoader<String, Optional<String>>() {
                    @Override
                    public Optional<String> load(String key) throws Exception {
                        return initOption(key);
                    }
                });
    }



    public Optional<String> initOption(String key) throws Exception {
        Map<String, String> cacheMap = Maps.newHashMap();
        cacheMap.put("a", "a");
        cacheMap.put("b", "b");
        cacheMap.put("c", "c");

        if (key.equals("d")) {
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
        System.out.println(cahceBuilder.get("e"));

        System.out.println("=================");
        Set<String> set = cahceBuilder.asMap().keySet();
        for (String s : set) {
            System.out.println(cahceBuilder.asMap().get(s));
        }
    }

    @Before
    public void junitInit1() {
        cahceBuilder1 = CacheBuilder.newBuilder().maximumSize(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return init(key);
                    }
                });
    }

    public String init(String key) throws Exception {
        Map<String, String> cacheMap = Maps.newHashMap();
        cacheMap.put("a", "a");
        cacheMap.put("b", "b");
        cacheMap.put("c", "c");

        if (key.equals("d")) {
           // cacheMap.put("d", "d");
            cahceBuilder1.put("d","d");
            return cahceBuilder1.get("d");
        }

        String value = cacheMap.get(key);
        return value;
    }

    @Test
    public void cahceBuilder1() throws Exception {
        //返回null则报错
        System.out.println(cahceBuilder1.get("a"));
        System.out.println(cahceBuilder1.get("b"));
        System.out.println(cahceBuilder1.get("c"));
        System.out.println(cahceBuilder1.get("d"));
       // System.out.println(cahceBuilder1.getUnchecked("e"));
        System.out.println(111);

    }

    @Test
    public void testStaticOrdering(){
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);


        System.out.println("list:"+ list);

        Ordering<Integer> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));
        System.out.println("usingToStringOrdering:"+ usingToStringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering:"+ arbitraryOrdering.sortedCopy(list));

        Integer min = Ordering.natural().onResultOf(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer input) {
                return input;
            }
        }).min(list);

        Integer max = Ordering.natural().onResultOf(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer input) {
                return input;
            }
        }).max(list);

        System.out.println("最小：" + min);
        System.out.println("最大：" + max);
    }

    @Test
    public void testExpire() throws InterruptedException {
        Cache<String, Integer> familyInviteLimitCache = CacheBuilder.newBuilder().maximumSize(1000)
                .expireAfterWrite(3, TimeUnit.SECONDS).build();
        familyInviteLimitCache.put("a",1);

        System.out.println(familyInviteLimitCache.getIfPresent("a"));

        Thread.sleep(5000);

        System.out.println(familyInviteLimitCache.getIfPresent("a"));
    }

}
