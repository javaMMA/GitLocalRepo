package com.xumingzhe.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2020/7/13.
 * Created by 许名哲
 * <p>
 * 作用：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Passport {

//    boolean loginSuccess() default true;

}