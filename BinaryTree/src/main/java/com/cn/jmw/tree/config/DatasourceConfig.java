package com.cn.jmw.tree.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月01日 10:32
 * @Version 1.0
 */
@Configuration
public class DatasourceConfig {

    @Bean(value = "clickhouseDB")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.clickhouse")
    public DataSource clickhouseDB() {
        // 通过配置地址拿到spring.datasource中的配置，创建一个DruidDataSource
        return new DruidDataSource();
    }

    @Bean(value = "mysqlDB")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource")
    public DataSource mysqlDB() {
        // 通过配置地址拿到spring.datasource中的配置，创建一个DruidDataSource
        return new DruidDataSource();
    }
}
