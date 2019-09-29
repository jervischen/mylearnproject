package com.example.demo.jianting;

/**
 * Created in 2019-05-21 17:33.
 *
 * @author chenxiao
 */
public class ReadListener  implements Listener{
    @Override
    public void isReading(Event event) {
        /**
         * 使用 event.getStudent()方法获取事件源的引用，然后执行相关操作
         */
        System.out.println("正在读书...");
    }
}
