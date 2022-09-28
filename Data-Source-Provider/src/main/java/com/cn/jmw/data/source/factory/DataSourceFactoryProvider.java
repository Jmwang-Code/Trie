package com.cn.jmw.data.source.factory;

import com.cn.jmw.data.source.factory.adapters.JdbcDataSourceAdapter;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:35
 * @Version 1.0
 */
public class DataSourceFactoryProvider {

    public static AbstractFactory getFactory(String choice){
        if("JDBC".equalsIgnoreCase(choice)){
            return new DataSourceFactory();
        }
        return null;
    }

    public static void main(String[] args) {
        JdbcDataSourceAdapter jdbc = DataSourceFactoryProvider.getFactory("JDBC").getJdbc("MYSQL");

    }
}
