package com.xumingzhe.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created on 2020/7/9.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Component
@Data
public class Dog {

    @Value("旺财")
    private String name;

    @Value("3")
    private Integer age;
}
