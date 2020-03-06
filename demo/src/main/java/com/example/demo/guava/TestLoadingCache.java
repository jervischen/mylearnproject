package com.example.demo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created in 2018-08-03 15:30.
 *
 * @author chenxiao
 */
public class TestLoadingCache {

    static Map<String, String> map = new HashMap<>();

    static {
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
    }

    static LoadingCache<String, Object> caches = CacheBuilder.newBuilder()
            .maximumSize(5)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    System.out.println(Thread.currentThread().getName() + "--" + key + " load in cache" + "-" + +System.currentTimeMillis());
                    Thread.sleep(1000);
                    return generateValueByKey(key);
                }
            });


    static LoadingCache<String, Object> caches1 = CacheBuilder.newBuilder()
            .maximumSize(5)
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    System.out.println(Thread.currentThread().getName() + "--" + key + " load in cache" + "-" + +System.currentTimeMillis());
                    return generateValueByKey(key);
                }
            });

    private static Object generateValueByKey(String key) {
        return map.get(key);
    }

    /**
     * 测试缓存穿透
     */
    public static void testCacheBreakdown() {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().build();
        ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), nameThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            // System.out.println(Thread.currentThread().getName() + "--" + System.currentTimeMillis());
                            System.out.println(Thread.currentThread().getName() + "--" + caches.get("a") + "---" + System.currentTimeMillis());
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 测试阻塞
     */
    public static void testCacheBlock() {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setDaemon(true).build();
        ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), nameThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 100; i++) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            // System.out.println(Thread.currentThread().getName() + "--" + System.currentTimeMillis());
                            System.out.println(Thread.currentThread().getName() + "--" + caches1.get("a") + "---" + System.currentTimeMillis());
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //定时刷新caches1得值
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("改变a的值" + "--" + System.currentTimeMillis());
                map.put("a", "b");
            }
        }, 2, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        // testCacheBreakdown();
//        testCacheBlock();
//
//        ReentrantLock lock = new ReentrantLock();
//        lock.lock();

        Set<String> set = new HashSet<>();
        set.add("1");

        System.out.println(set.contains(String.valueOf(1)));
    }

}
