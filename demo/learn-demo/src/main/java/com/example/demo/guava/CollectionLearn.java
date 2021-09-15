package com.example.demo.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import org.junit.Test;
import org.testng.collections.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author Chen Xiao
 * @since 2020-03-17 11:10
 */
public class CollectionLearn {

    @Test
    public void listLearn() {
        List<String> list = Lists.newArrayList("one", "two");

        //快速创建不可变的list
        List<String> imList = ImmutableList.of("one", "two", "three", "one", "three");
        //复制列表
        imList = ImmutableList.copyOf(list);
//        imList.add("11");

        //快速创建不可变的map
        Map<String, String> map = ImmutableMap.of("one", "1", "two", "2");

        System.out.println(map);
        map.put("one","a");
    }

    @Test
    public void setLearn(){
        //无序可重复Multiset
        Multiset multiset = HashMultiset.create();
    }
}
