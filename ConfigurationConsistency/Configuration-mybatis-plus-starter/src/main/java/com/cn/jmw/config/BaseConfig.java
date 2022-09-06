package com.cn.jmw.config;

import com.eoi.fly.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        log.info("┌───────────────────────────────┐");
        log.info("│  initialization MyBatis-Plus  │");
        log.info("└───────────────────────────────┘");
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }
}
