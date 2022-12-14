package com.cn.jmw;

import com.cn.jmw.uitls.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuwukai
 * @date 2021.11.18
 */
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
        log.info("=============初始化==Database===============");
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }
}
