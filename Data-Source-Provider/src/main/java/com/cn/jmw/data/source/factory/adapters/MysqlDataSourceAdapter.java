package com.cn.jmw.data.source.factory.adapters;

import com.cn.jmw.data.source.pojo.PageInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:46
 * @Version 1.0
 */
public class MysqlDataSourceAdapter extends JdbcDataSourceAdapter{

    @Override
    public String readCurrDatabase(Connection conn, boolean isCatalog) throws SQLException {
        return null;
    }

    @Override
    public int executeCountSql(String sql) throws SQLException {
        return 0;
    }

    @Override
    public int executeSql(String sql, PageInfo pageInfo) throws SQLException {
        return 0;
    }


}
