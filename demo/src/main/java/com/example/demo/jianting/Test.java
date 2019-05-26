package com.example.demo.jianting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2019-05-21 17:33.
 *
 * @author chenxiao
 */
public class Test {
    public static void main(String[] args) {
        ReadListener readListener = new ReadListener();
        Student student = new Student();
        student.setReadListener(readListener);
        // 当执行这个方法时，会自动调用ReadListener.isReading()方法
        student.read();
    }
}
