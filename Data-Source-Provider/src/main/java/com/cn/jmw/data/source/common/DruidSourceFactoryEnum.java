package com.cn.jmw.data.source.common;

/**
 * @author jmw
 * @Description Druid连接池枚举类
 * @date 2022年09月28日 17:22
 * @Version 1.0
 */
public enum DruidSourceFactoryEnum {

    DEFAULT_MAX_WAIT("5000");
    String jdbc;
    DruidSourceFactoryEnum(String jdbc) {
        this.jdbc = jdbc;
    }

    public Integer getIntegerValue(){
        return Integer.valueOf(jdbc);
    }

    public String getStringValue(){
        return this.jdbc;
    }
}
