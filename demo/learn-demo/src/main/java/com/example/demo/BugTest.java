package com.example.demo;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.collections.Sets;

import java.util.Random;
import java.util.Set;

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
    }
    @Test
    public void testRandom(){
        Random random = new Random(1);
        for (int i=0;i <5;i++){
            System.out.println(random.nextInt(100));
        }

        Set<Long> list = Sets.newHashSet();
        list.add(1239L);
        list.add(null);
        list.add(null);
        System.out.println(list);
        list.remove(null);
        System.out.println(list);
    }

    @Test
    public void tes(){
        String a  =" a b c ";
        System.out.println(a.trim());
    }
}
