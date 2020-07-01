package com.example.demo.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created in 2018-02-06 10:41.
 *
 * @author chenxiao
 */
@Data
//@Builder
//@AllArgsConstructor
public class Content {
    /**
     * 红包id
     */
    private long id;

   // private long belongId;

    private String text;


    public static void main(String[] args) {
       try {
           System.out.println(11);
           return;
       }finally {
           System.out.println(333);
       }
    }
}
