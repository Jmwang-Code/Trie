package com.cn.jmw.pojo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月14日 11:34
 * @Version 1.0
 */
@Data
@Configuration
@PropertySource(value = {"classpath:application-trie.yml"})
public class OracleConfig {
    @Value("${usePasswordService}")
    private boolean usePasswordService = false;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Value("${driver-class-name}")
    private String driverClassName;
}
