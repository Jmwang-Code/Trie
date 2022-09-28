package com.cn.jmw.data.source.factory;

import com.cn.jmw.data.source.factory.adapters.JdbcDataSourceAdapter;
import com.cn.jmw.data.source.factory.adapters.MysqlDataSourceAdapter;

import javax.sql.DataSource;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:32
 * @Version 1.0
 */
public class DataSourceFactory<T extends DataSource> extends AbstractFactory{

    @Override
    public JdbcDataSourceAdapter getJdbc(String type){
        if ("".equals(type)){
            return new MysqlDataSourceAdapter();
        }
        return null;
    }
}
