package com.example.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created in 2018-02-12 16:24.
 *
 * @author chenxiao
 */
public class TestRegularExpression {
    private static Logger logger = LoggerFactory.getLogger(TestRegularExpression.class);


    /**
     * 中文、数字、英文
     */
    @Test
    public void testRegularChinaAndNumAndEglish() {
        String str = "abc123哈哈!";
        String regEx = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$";

        String newStr = str.replaceAll(regEx, "");
        System.out.println(newStr);
        testReg(str,regEx);
    }

    public void testReg(String regEx,String str) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println(m.matches());
    }
}
