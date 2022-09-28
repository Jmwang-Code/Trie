package com.cn.jmw.data.source.factory;

import com.cn.jmw.data.source.pojo.JdbcProperties;

import javax.sql.DataSource;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:29
 * @Version 1.0
 */
public abstract class AbstractFactory<T extends DataSource> {

    /**
     * @Author jmw
     * @Description 获取JDBC
     * @param type: 具体枚举
     * @return JdbcDataSourceAdapter
     * @Date 15:18 2022/9/28
     */
    public abstract T createDataSource(JdbcProperties jdbcProperties) throws Exception;
}
