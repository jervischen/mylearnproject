package com.example.current;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Chen Xiao
 * @since 2020-07-30 18:12
 */
public class ReentrantLockTest implements Runnable{
    private Integer key = 0;

    // 锁对象
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        // 需要结果是key实现自增长，如果没有同步块，则可能会出现重复key值的现象
        lock.lock();
        try {
            key++;

            System.out.println(Thread.currentThread().getName() + ":" + key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }finally{
            // 上述代码实现功能与使用sychronized同步代码块一样。
            // sychronized同步代码块或同步方法在代码执行完之后锁自动释放；而用Lock则需要手工释放锁。
            // 为了保证锁最终被释放，释放锁代码放在finally块内。
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lt = new ReentrantLockTest();

        for(int i=0; i<3; i++) {
            new Thread(lt, "Thread" + i).start();
        }
    }
}
