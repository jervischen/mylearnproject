package com.example.demo;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created in 2018-01-22 11:46.
 *
 * @author chenxiao
 */
public class TestGuava1 {
    private static Logger logger = LoggerFactory.getLogger(TestGuava1.class);


    /**
     * 谷歌LoadingCache缓存使用
     *
     * @param args
     */
    static LoadingCache<String, Optional<List<Integer>>> cahceBuilder;


    @Before
    public void junitInit() {
        cahceBuilder = CacheBuilder.newBuilder().maximumSize(2)
                .build(new CacheLoader<String, Optional<List<Integer>>>() {
                    @Override
                    public Optional<List<Integer>> load(String key) throws Exception {
                        return initOption(key);
                    }
                });
    }


    public Optional<List<Integer>> initOption(String key) throws Exception {
        if (key.equalsIgnoreCase("a")){
            return Optional.of(Lists.newArrayList(1,2,3));
        }else if(key.equalsIgnoreCase("b")){
            return Optional.of(Lists.newArrayList(4,5,6));
        }

        return Optional.absent();
    }



    @Test
    public void testNull() throws Exception {
        System.out.println("loadCache 大小：" + cahceBuilder.size());
        System.out.println(cahceBuilder.get("a").get());;
        System.out.println("loadCache 大小：" + cahceBuilder.size());
        System.out.println(cahceBuilder.get("b").get());;
        System.out.println("loadCache 大小：" + cahceBuilder.size());

        System.out.println(cahceBuilder.stats());
    }


}
