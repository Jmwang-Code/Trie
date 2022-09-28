package com.cn.jmw.data.source.factory.adapters;

import java.sql.Connection;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:46
 * @Version 1.0
 */
public class MysqlDataSourceAdapter implements JdbcDataSourceAdapter{


    @Override
    public String readCurrDatabase(Connection conn, boolean isCatalog) {
        return null;
    }
}
