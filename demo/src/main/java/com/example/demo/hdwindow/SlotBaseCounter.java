package com.example.demo.hdwindow;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created in 2019-05-27 10:42.
 *
 * @author chenxiao
 */
public class SlotBaseCounter {
    private int slotSize;
    private AtomicInteger[] slotCounter;

    public SlotBaseCounter(int slotSize) {
        slotSize = slotSize < 1 ? 1 : slotSize;
        this.slotSize = slotSize;
        this.slotCounter = new AtomicInteger[slotSize];
        for (int i = 0; i < this.slotSize; i++) {
            slotCounter[i] = new AtomicInteger(0);
        }
    }

    /**
     * 增加
     * @param slotSize
     */
    public void increaseSlot(int slotSize) {
        slotCounter[slotSize].incrementAndGet();
    }

    /**
     * 擦除
     * @param slotSize
     */
    public void wipeSlot(int slotSize) {
        slotCounter[slotSize].set(0);
    }

    /**
     * 总数
     * @return
     */
    public int totalCount() {
        return Arrays.stream(slotCounter).mapToInt(slotCounter -> slotCounter.get()).sum();
    }

    @Override
    public String toString() {
        return Arrays.toString(slotCounter);
    }
}
