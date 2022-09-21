package com.tuling.datasource.dynamic.mybatis.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiTransactional {

    String[] value() default {};
}
