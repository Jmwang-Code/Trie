package com.cn.jmw.pojo.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月14日 11:35
 * @Version 1.0
 */
@Data
@Slf4j
@Configuration
@PropertySource(value = {"classpath:application-trie.yml"})
public class SqliteConfig {
    private Map<String,String> urls;

    @Value("${driver-class-name}")
    private String driverClassName;

    public static Connection getConnection(String file) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        String url = "jdbc:sqlite:" + file;
        log.info("加载SqlLite数据库，URL:" + url);
        try {
            Properties prop = new Properties();
            // 解決SQLITE的日期格式问题
            prop.put("date_string_format", "yyyy-MM-dd HH:mm:ss");
            return DriverManager.getConnection(url, prop);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
