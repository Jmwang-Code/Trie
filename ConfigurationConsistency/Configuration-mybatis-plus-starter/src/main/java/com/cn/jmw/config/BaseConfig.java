package com.cn.jmw.config;

import com.cn.jmw.uitls.SpringUtils;
import com.cn.jmw.utils.StartLogPrinting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jmw
 * @Description //TODO
 * @Date 13:50 2022/9/6
 **/
@Configuration
@Slf4j
public abstract class BaseConfig {

    /**
     * Spring 工具类
     *
     * @param applicationContext
     * @return
     */
    @Bean
    public SpringUtils getSpringUtils(ApplicationContext applicationContext) {
        StartLogPrinting.SingletonEnum.SINGLETON.getInstance()
                .add("initialization MyBatis-Plus")
                .build();
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }
}
