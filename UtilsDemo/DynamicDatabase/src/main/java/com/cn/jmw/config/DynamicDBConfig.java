package com.cn.jmw.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 一只小小狗
 * @version 1.0.0
 * @ClassName DynamicDBConfig.java
 * @Description TODO
 * @createTime 2022年09月17日 21:21:00
 */
@Configuration
public class DynamicDBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() {
        return new DruidDataSource();
    }
}
