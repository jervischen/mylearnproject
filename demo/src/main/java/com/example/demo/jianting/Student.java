package com.example.demo.jianting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2019-05-21 17:32.
 *
 * @author chenxiao
 */
public class Student {
    // read方法事件监听器引用
    private Listener listener;

    /**
     * @description: 注册read方法的事件监听器
     * @param listener 事件监听器引用
     * @return void
     * @throws
     */
    public void setReadListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * @description: 学生的read方法
     * @return void
     * @throws
     */
    public void read() {
        if (listener != null) {
            Event event = new Event();
            event.setStudent(this);
            // 学生正在读书...
            listener.isReading(event);
        }
    }
}
