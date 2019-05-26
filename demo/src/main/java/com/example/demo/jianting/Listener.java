package com.example.demo.jianting;

/**
 * Created in 2019-05-21 17:32.
 *
 * @author chenxiao
 */
public interface Listener {
    /**
     * @description: 学生读书触发的事件
     * @param event 事件对象引用，通过这个引用，获取事件源的引用，然后就可以对事件源进行操作
     * @return void
     * @throws
     */
    public void isReading(Event event);

}
