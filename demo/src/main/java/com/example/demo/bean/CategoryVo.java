package com.example.demo.bean;



/**
 * Created in 2018-08-21 11:30.
 *
 * @author chenxiao
 */
public class CategoryVo {


    private long id;
    /**
     * 完成次数
     */
    private int num;

    /**
     *
     */
    private int status;


    private long startTime;


    private long endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
