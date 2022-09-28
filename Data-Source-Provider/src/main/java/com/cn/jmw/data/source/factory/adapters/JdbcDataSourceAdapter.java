package com.cn.jmw.data.source.factory.adapters;

import java.sql.Connection;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:38
 * @Version 1.0
 */
public interface JdbcDataSourceAdapter {

   
    /**
     * @Author jmw
     * @Description 
     * @param conn: 
     * @param isCatalog: 
     * @return String
     * @Date 15:03 2022/9/28
     */
    public String readCurrDatabase(Connection conn, boolean isCatalog);
}
