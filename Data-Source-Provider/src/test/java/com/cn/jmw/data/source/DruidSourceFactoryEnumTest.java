package com.cn.jmw.data.source;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cn.jmw.data.source.factory.DataSourceFactoryProvider;
import com.cn.jmw.data.source.pojo.JdbcProperties;
import com.cn.jmw.data.source.pojo.common.AbstractFactoryEnum;
import com.cn.jmw.data.source.pojo.common.DruidSourceFactoryEnum;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月28日 17:25
 * @Version 1.0
 */
public class DruidSourceFactoryEnumTest {

    public static void main(String[] args) {
        try {
            DataSource dataSource = DataSourceFactoryProvider.getFactory(
                    AbstractFactoryEnum.DRUID).createDataSource(
                    new JdbcProperties()
                            .setProperties(new Properties())
                            .setUrl("jdbc:mysql://152.136.154.249:3306/demo?&allowMultiQueries=true&characterEncoding=utf-8")
                            .setUser("demo")
                            .setPassword("kKJ8XynXLzjfYDA7")
                            .setDriverClass("com.mysql.cj.jdbc.Driver")
            );
//            Properties properties = new Properties();
//            properties.setProperty(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, "com.mysql.cj.jdbc.Driver");
//            properties.setProperty(DruidDataSourceFactory.PROP_URL, "jdbc:mysql://152.136.154.249:3306/demo?&allowMultiQueries=true&characterEncoding=utf-8&useSSL=false");
//            properties.setProperty(DruidDataSourceFactory.PROP_USERNAME, "demo");
//            properties.setProperty(DruidDataSourceFactory.PROP_PASSWORD, "kKJ8XynXLzjfYDA7");
//            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

            Connection connection = dataSource.getConnection();
            ResultSet resultSet = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("select * from role");
            while (resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                String columnName = metaData.getColumnName(1);
                System.out.println(columnName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
