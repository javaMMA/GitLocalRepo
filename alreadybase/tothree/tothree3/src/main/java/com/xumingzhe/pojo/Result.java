package com.xumingzhe.pojo;

import lombok.Data;

/**
 * Created on 2020/7/14.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Data
public class Result<T> {

    private T data;
    private String msg;
    private Integer code;


    public Result(T data) {
        this.data = data;
        this.msg = "成功";
        this.code = 200;
    }

    public Result(T data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
}
