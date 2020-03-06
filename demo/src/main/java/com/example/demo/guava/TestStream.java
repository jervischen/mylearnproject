package com.example.demo.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.istack.internal.Nullable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Chen Xiao
 * @since 2019-12-05 18:09
 */
public class TestStream {


    @Test
    public void test() {
        ArrayList<Integer> list = Lists.newArrayList();
        list.add(123);
        list.add(111);
        list.add(444);
        list.add(555);

        ArrayList<Integer> integers = Lists.newArrayList(Collections2.filter(list, new Predicate<Integer>() {

            @Override
            public boolean apply(@Nullable Integer input) {
                return input > 123;
            }
        }));

        System.out.println(list);
        System.out.println(integers);


    }

    @Test
    public void testA() {
        String str = "罗长";
        byte[] sb = str.getBytes();
        System.out.println(sb);

        List list = new ArrayList();
        list.add(2);
        System.out.println(list);

        Result result = new Result();
        result.setA(1);
        System.out.println(1);
    }


    @Test
    public void testB() {
        byte[] b = {(byte) 0xB8, (byte) 0xDF, (byte) 0xCB, (byte) 0xD9};
        String str = new String(b);
        System.out.println(str);

    }

    /**
     * 过滤看过的视频
     *
     * @param userId
     */
    public static void a(long userId, List<Long> result) {
        Set<String> videoIds = Sets.newHashSet();
        videoIds.add("123");

        ArrayList<Long> list = Lists.newArrayList(Collections2.filter(result, new Predicate<Long>() {
            @Override
            public boolean apply(@Nullable Long input) {
                for (String id : videoIds) {
                    return input == Long.valueOf(id);
                }
                return false;
            }
        }));

        System.out.println(result);
        System.out.println(list);
    }


    public static void main(String[] args) {
        ArrayList<Long> objects = Lists.newArrayList();
        objects.add(345L);
        objects.add(123L);
        objects.add(789L);

        System.out.println(objects.subList(0,3));
        System.out.println(objects.subList(3 ,3));


        System.out.println(5/2);
    }


}
