package com.example.demo.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-02-01 19:53.
 *
 * @author chenxiao
 */

public class MyData {

    private boolean isFull;

    private boolean urlShareable=false;



    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public boolean isUrlShareable() {
        return urlShareable;
    }

    public void setUrlShareable(boolean urlShareable) {
        this.urlShareable = urlShareable;
    }
}
