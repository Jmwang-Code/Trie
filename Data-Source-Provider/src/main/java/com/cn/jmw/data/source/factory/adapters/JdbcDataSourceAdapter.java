package com.cn.jmw.data.source.factory.adapters;


import com.cn.jmw.data.source.exception.Exceptions;
import com.cn.jmw.data.source.factory.DataSourceFactoryProvider;
import com.cn.jmw.data.source.pojo.JdbcDriverInfo;
import com.cn.jmw.data.source.pojo.JdbcProperties;
import com.cn.jmw.data.source.pojo.PageInfo;
import com.cn.jmw.data.source.common.AbstractFactoryEnum;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:38
 * @Version 1.0
 */
public abstract class JdbcDataSourceAdapter implements Closeable {

    protected DataSource dataSource;
    protected JdbcProperties jdbcProperties;
    protected JdbcDriverInfo driverInfo;
    protected boolean init;

    public final void init(JdbcProperties jdbcProperties, JdbcDriverInfo driverInfo) {
        try {
            this.jdbcProperties = jdbcProperties;
            this.driverInfo = driverInfo;
            this.dataSource = DataSourceFactoryProvider.getFactory(AbstractFactoryEnum.DRUID).createDataSource(jdbcProperties);
        } catch (Exception e) {
            log.error("dataSource provider init error", e);
            Exceptions.e(e);
        }
        this.init = true;
    }

    /**
     * @Author jmw
     * @Description 
     * @param conn: 
     * @param isCatalog: 
     * @return String
     * @Date 15:03 2022/9/28
     */
    public abstract String readCurrDatabase(Connection conn, boolean isCatalog) throws SQLException;

    /**
     * @Author jmw
     * @Description 执行多条
     * @param sql:
     * @return int
     * @Date 17:12 2022/9/29
     */
    public abstract int executeCountSql(String sql) throws SQLException;

    /**
     * @Author jmw
     * @Description 执行一条
     * @param sql:
     * @param pageInfo:
     * @return int
     * @Date 17:12 2022/9/29
     */
    public abstract int executeSql(String sql, PageInfo pageInfo) throws SQLException;

    @Override
    public void close() throws IOException {

    }
}
