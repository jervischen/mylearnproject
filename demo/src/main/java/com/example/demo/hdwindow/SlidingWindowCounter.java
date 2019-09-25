package com.example.demo.hdwindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2019-05-27 10:42.
 *
 * @author chenxiao
 */
public class SlidingWindowCounter {
    private volatile SlotBaseCounter slotBaseCounter;
    private volatile int windowSize;
    private volatile int head;

    /**
     * 滑动
     * @param windowSize
     */
    public SlidingWindowCounter(int windowSize) {
        resizeWindow(windowSize);
    }

    public synchronized void resizeWindow(int windowSize) {
        this.windowSize = windowSize;
        this.slotBaseCounter = new SlotBaseCounter(windowSize);
        this.head = 0;
    }

    public void increase() {
        slotBaseCounter.increaseSlot(head);
    }

    public int totalAndAdvance() {
        int total = totalCount();
        advance();
        return total;
    }

    public void advance() {
        int tail = (head + 1) % windowSize;
        slotBaseCounter.wipeSlot(tail);
        head = tail;
    }

    public int totalCount() {
        return slotBaseCounter.totalCount();
    }

    @Override
    public String toString() {
        return "SlotBaseCounter.total = " + totalCount() + " SlidingWindowCounter.head = " + head + " >> " + slotBaseCounter;
    }
}
