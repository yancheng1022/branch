package com.kaka.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Yancheng Guo
 * @Date: 2019/3/31 10:42
 * @Description: 用来标记线程不安全的类
*/
//作用目标是一个类
@Target(ElementType.TYPE)
//注解存在的范围(只是当标识使用 并不编译)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {
    String value() default "";
}