package com.example.demo;

import com.example.demo.bean.MyData;
import com.example.demo.util.BeanUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.util.*;

/**
 * Created in 2018-11-13 16:11.
 *
 * @author chenxiao
 */
public class TestUtil {
    @Test
    public void testBeanUtil() {
        Map<String, Object> m = Maps.newHashMap();
        MyData myData = new MyData();
        Map<String, Object> map = BeanUtil.transBean2Map(myData);

        m.put("time", map);
        m.put("time1", myData);
        System.out.println(map.toString());
        System.out.println(m.toString());


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("d");

        ArrayList<Object> objects = Lists.newArrayList();


        for (Object o : list.toArray(new String[0])) {
            System.out.println(o);
        }
    }

    @Test
    public void testContain() {
        String nj = "112223344,666";
        System.out.println(nj.contains("334"));

        int a = 2;
        int b = 6;
        System.out.println(a & b);
    }

    @Test
    public void a() {
        ImmutablePair<Object, Object> pair = ImmutablePair.of(null, null);
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight() == null);

        ImmutablePair<Object, Object> pair1 = null;
        System.out.println(pair1 == null);

    }


    @Test
    public void b() {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        System.out.println(list.subList(1, 2));

        System.out.println(String.valueOf(new Date()));

        System.out.println(Boolean.TRUE.toString());

        String a = "1000000000000000000";
        System.out.println(Long.parseLong(a));

        System.out.println(00/20);

    }

}
