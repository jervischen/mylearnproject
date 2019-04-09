package com.example.demo;

import com.example.demo.bean.MyData;
import com.example.demo.util.BeanUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created in 2018-11-13 16:11.
 *
 * @author chenxiao
 */
public class TestUtil {
    @Test
    public void testBeanUtil(){
        Map<String, Object> m = Maps.newHashMap();
        MyData myData = new MyData();
        Map<String, Object> map = BeanUtil.transBean2Map(myData);

        m.put("time",map);
        m.put("time1",myData);
        System.out.println(map.toString());
        System.out.println(m.toString());


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("d");

        ArrayList<Object> objects = Lists.newArrayList();


        for (Object o :  list.toArray(new String[0])) {
            System.out.println(o);
        }
    }
}
