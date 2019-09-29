package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created in 2018-02-06 10:41.
 *
 * @author chenxiao
 */
@Data
@Builder
@AllArgsConstructor
public class Content {
    /**
     * 红包id
     */
    private long id;

   // private long belongId;

    private String text;

    public String getText() {
        return "aaa";
    }
}
