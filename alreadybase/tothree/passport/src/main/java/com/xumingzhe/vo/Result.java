package com.xumingzhe.vo;

import lombok.Data;

import java.util.PrimitiveIterator;

/**
 * Created on 2020/7/14.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Data
public class Result<T> {

    private T data;
    private meta meta;

    @Data
     class meta {

        private String msg;
        private Integer status;

        public meta(String msg, Integer status) {
            this.msg = msg;
            this.status = status;
        }
    }

    public Result(T data) {
        this.data = data;
        this.meta = new meta("成功",200);
    }

    public Result(T data,String msg,Integer status) {
        this.data = data;
        this.meta = new meta(msg,status);
    }
}
