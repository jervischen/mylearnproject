package com.example.demo.jianting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2019-05-21 17:33.
 *
 * @author chenxiao
 */
public class Event {
    // 放置事件源的引用
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}