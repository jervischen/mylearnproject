package com.example.demo;

import com.example.demo.util.Student;
import com.example.demo.util.TestObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created in 2018-06-26 10:53.
 *
 * @author chenxiao
 */
public class BugTest {
    private static Logger logger = LoggerFactory.getLogger(BugTest.class);

    @Test
    public void redBao(){
        String password = "大可爱(๑•.•๑)";
        String str = Hashing.md5().newHasher().putString(password, Charsets.UTF_8).hash().toString()
                .substring(8, 24);
        System.out.println(str);

        BlockingDeque<TestObject> que = new LinkedBlockingDeque();
        TestObject testObject =new TestObject();
        testObject.setAge(11);
        testObject.setName("111");

        TestObject testObject1 =new TestObject();
        testObject1.setAge(22);
        testObject1.setName("222");
        que.add(testObject);
        que.add(testObject1);
        System.out.println(que);

        Set<String> randomSet = new HashSet<>(3);
        randomSet.add("a");
        randomSet.add("b");
        randomSet.add("a");
        System.out.println(randomSet.size());
        for (String s : randomSet) {
            System.out.println(s);
        }
        List<String> list = new ArrayList();
        list.addAll(randomSet);
        System.out.println(list);

    }


    public boolean te(){
        int i =5;
        switch (i){
            case 1:
                break;
            case 3:
                break;
            case 5:
               // return true;
                break;

        }
        return false;
    }
}
