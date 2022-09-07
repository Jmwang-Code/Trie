package com.cn.jmw;

import com.cn.jmw.uitls.SpringUtils;
import com.cn.jmw.utils.StartLogPrinting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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
        List list = new ArrayList<>();
        list.add("initialization   Database");
        StartLogPrinting.startLog(list);
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }
}
