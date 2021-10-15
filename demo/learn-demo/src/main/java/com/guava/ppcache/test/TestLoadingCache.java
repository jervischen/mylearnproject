package com.guava.ppcache.test;


import com.guava.ppcache.cache.PPLoadingCacheManager;
import com.guava.ppcache.bean.LoadingCacheConf;
import com.guava.ppcache.bean.ThreadPoolConf;
import com.guava.ppcache.executor.ExecutorFactory;
import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Chen Xiao
 * @since 2021-10-12 18:10
 */
@Slf4j
public class TestLoadingCache {
    static HashSet<Integer> cache = new HashSet<>();
    static int cacheCount = 0;
    static int totalCount = 0;

    volatile int count = 1;

    CountDownLatch latch = new CountDownLatch(1);


    public Optional<String> load(String str) {
        log.info("{} begin to mock query db...", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} success to mock query db...", Thread.currentThread().getName());
        return Optional.of(str);
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        TestLoadingCache t = new TestLoadingCache();
        t.testGetV();
    }

    public void testGetV() throws IOException {
        String cahce1 = TestLoadingCache.class.getName() + "_1";
        String cahce2 = TestLoadingCache.class.getName() + "_2";

        PPLoadingCacheManager.getInstance()
                .createLoadingCache(cahce1
                        , new LoadingCacheConf()
                        , s -> this.load((String) s));


        new Thread(() -> getV(cahce1, "keyA")).start();
        new Thread(() -> getV(cahce1, "keyA")).start();
        new Thread(() -> getV(cahce1, "keyA")).start();
        new Thread(() -> getV(cahce1, "keyA")).start();
        new Thread(() -> getV(cahce1, "keyA")).start();

        PPLoadingCacheManager.getInstance()
                .createLoadingCache(cahce2
                        , new LoadingCacheConf()
                        , s -> this.load((String) s));

        new Thread(() -> getV(cahce2, "keyB")).start();
        new Thread(() -> getV(cahce2, "keyB")).start();
        new Thread(() -> getV(cahce2, "keyB")).start();
        new Thread(() -> getV(cahce2, "keyB")).start();
        new Thread(() -> getV(cahce2, "keyB")).start();
        System.in.read();
    }

    private void getV(String cacheName, String key) {
        try {
            for (int i = 100; i < 102; i++) {
                Optional<String> name = (Optional<String>) PPLoadingCacheManager.getCacheByName(cacheName).get(key);
                log.info("缓存[{}] value[{}]", cacheName, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testExcute() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = ExecutorFactory.getInstance().create(new ThreadPoolConf()
                .setCorePoolSize(1)
                .setMaximumPoolSize(3)
                .setCapacity(3));

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.submit(()-> {
                try {
                    System.out.println(finalI);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.in.read();
    }
}
