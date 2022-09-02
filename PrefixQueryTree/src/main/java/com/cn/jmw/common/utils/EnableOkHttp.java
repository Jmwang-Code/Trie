package com.cn.jmw.common.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cn.jmw.common.config.OkHttpConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @Author jmw
 * @Description //TODO
 * @Date 18:14 2022/9/2
 * @Param
 * @return
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OkHttpConfiguration.class)
public @interface EnableOkHttp {

}