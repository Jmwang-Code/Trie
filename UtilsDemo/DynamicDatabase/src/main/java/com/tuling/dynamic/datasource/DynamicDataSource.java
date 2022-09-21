package com.tuling.dynamic.datasource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
@Primary   // 将该Bean设置为主要注入Bean
public class DynamicDataSource extends AbstractRoutingDataSource {


    // 当前使用的数据源标识
    public static ThreadLocal<String> name=new ThreadLocal<>();

    // 写
    @Autowired
    DataSource dataSource1;
    // 读
    @Autowired
    DataSource dataSource2;


    // 返回当前数据源标识
    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();

    }


    @Override
    public void afterPropertiesSet() {

        // 为targetDataSources初始化所有数据源
        Map<Object, Object> targetDataSources=new HashMap<>();
        targetDataSources.put("W",dataSource1);
        targetDataSources.put("R",dataSource2);

        super.setTargetDataSources(targetDataSources);

        // 为defaultTargetDataSource 设置默认的数据源
        super.setDefaultTargetDataSource(dataSource1);

        super.afterPropertiesSet();
    }
}
