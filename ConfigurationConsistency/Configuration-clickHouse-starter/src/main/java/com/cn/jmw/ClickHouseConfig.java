package com.cn.jmw;

import com.cn.jmw.uitls.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月30日 12:49
 * @Version 1.0
 */
@Configuration
@Slf4j
public class ClickHouseConfig {
//😂
    /**
     * Spring 工具类
     */
    @Bean
    public SpringUtils getSpringUtils(ApplicationContext applicationContext) {
        log.info("┌───────────────────────────────┐");
        log.info("│   initialization ClickHouse   │");
        log.info("└───────────────────────────────┘");
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }
}
