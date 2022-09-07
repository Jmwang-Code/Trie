package com.cn.jmw;

import com.cn.jmw.uitls.SpringUtils;
import com.cn.jmw.utils.StartLogPrinting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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
        List list = new ArrayList<>();
        list.add("initialization ClickHouse");
        StartLogPrinting.startLog(list);
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }
}
