package com.cn.jmw.data.source.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cn.jmw.data.source.pojo.JdbcProperties;
import com.cn.jmw.data.source.pojo.common.DruidSourceFactoryEnum;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:32
 * @Version 1.0
 */
public class HikariSourceFactory<T extends DataSource> extends AbstractFactory{

    @Override
    public DataSource createDataSource(JdbcProperties jdbcProperties) throws Exception {
        Properties properties = configDataSource(jdbcProperties);
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDataSourceProperties(properties);
        log.info("druid data source created ({})", hikariDataSource.getUsername());
        return hikariDataSource;
    }

    /**
     * @Author jmw
     * @Description 属性解析
     * @param properties:
     * @return Properties
     * @Date 18:02 2022/9/28
     */
    private Properties configDataSource(JdbcProperties properties) {
        Properties pro = new Properties();
        pro.setProperty(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, properties.getDriverClass());
        pro.setProperty(DruidDataSourceFactory.PROP_URL, properties.getUrl());
        if (properties.getUser() != null) {
            pro.setProperty(DruidDataSourceFactory.PROP_USERNAME, properties.getUser());
        }
        if (properties.getPassword() != null) {
            pro.setProperty(DruidDataSourceFactory.PROP_PASSWORD, properties.getPassword());
        }
        pro.setProperty(DruidDataSourceFactory.PROP_MAXWAIT, DruidSourceFactoryEnum.DEFAULT_MAX_WAIT.getStringValue());
        System.setProperty("hikari.mysql.usePingMethod", "false");
        pro.putAll(properties.getProperties());
        return pro;
    }
}
