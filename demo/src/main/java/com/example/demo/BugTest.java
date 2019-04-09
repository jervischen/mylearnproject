package com.example.demo;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
