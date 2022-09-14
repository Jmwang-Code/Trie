package com.cn.jmw.pojo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月14日 17:34
 * @Version 1.0
 */
@Data
@Component
@PropertySource(value = {"classpath:application-trie.yml"})
@ConfigurationProperties("trie.codes")
public class CodesConfig {
    private List<Code> code;
}

@Data
@Component
@PropertySource(value = {"classpath:application-trie.yml"})
@ConfigurationProperties(prefix="code")
 class Code {
    private String id;
    private String name;
    private String type;
    private String prefix;
    private String enable;
    private List<Table> tables;
}

@Data
@Component
@PropertySource(value = {"classpath:application-trie.yml"})
@ConfigurationProperties(prefix="table")
 class Table {
    private String id;
    private String name;
    private String enable;
    private String remoteHandler;
    private String selectSql;
}