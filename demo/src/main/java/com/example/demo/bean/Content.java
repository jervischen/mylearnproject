package com.example.demo.bean;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-02-06 10:41.
 *
 * @author chenxiao
 */
@Data
@ToString
@Builder
public class Content {
    /**
     * 红包id
     */
    private long id;

    private long belongId;

    private String text;

}
