package com.xumingzhe.pojo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.lang.String;

/**
 * Created on 2020/7/9.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {

    private String lastName;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
}
