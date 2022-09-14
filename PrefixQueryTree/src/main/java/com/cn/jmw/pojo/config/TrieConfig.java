package com.cn.jmw.pojo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月14日 11:17
 * @Version 1.0
 */
@Data
@Configuration
@PropertySource(value = {"classpath:application-trie.yml"})
@ConfigurationProperties(prefix = "trie",ignoreUnknownFields = true)
public class TrieConfig {

    @Value("${enableTrieCompress}")
    private boolean enableTrieCompress = false;

    @Value("${enableTrieInverted}")
    private boolean enableTrieInverted = false;

    @Value("${disableTrieLoad}")
    private boolean disableTrieLoad = false;

    @Value("${enableTrieAllSearch}")
    private boolean enableTrieAllSearch = false;

    private final MysqlConfig mysqlConfig;

    private final OracleConfig oracleConfig;

    private final SqliteConfig sqliteConfig;

    private ClickhouseConfig clickhouseConfig;

    private ElasticsearchConfig elasticsearchConfig;

    private RedisConfig redisConfig;

    private final CodesConfig codesConfig;

    private CacheConfig cacheConfig;

    private OplogConfig oplogConfig;

    public TrieConfig(MysqlConfig mysqlConfig, OracleConfig oracleConfig, SqliteConfig sqliteConfig, CodesConfig codesConfig) {
        this.mysqlConfig = mysqlConfig;
        this.oracleConfig = oracleConfig;
        this.sqliteConfig = sqliteConfig;
        this.codesConfig = codesConfig;
    }

}
