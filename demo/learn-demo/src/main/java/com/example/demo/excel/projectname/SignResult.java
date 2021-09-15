package com.example.demo.excel.projectname;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 上上签数据返回统一结构体
 *
 * @author Chen Xiao
 * @since 2020-05-27 15:00
 */
@Data
@AllArgsConstructor
public class SignResult<T> {

    /**
     * 错误码, 如果为0表示成功,没有错误
     */
    private int code;

    /**
     * 错误描述
     */
    private String message;

    /**
     * 输出数据 （如果有的话，具体内容和每一个具体的接口相关）
     */
    private T data;
}
