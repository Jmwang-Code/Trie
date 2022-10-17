package com.cn.jmw.model;

import com.cn.jmw.data.provider.base.OuterDataSourceManager;
import com.cn.jmw.data.provider.base.entity.DataSourceProviderEntity;
import com.cn.jmw.data.provider.base.response.ResponseBody;

import java.util.HashMap;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年10月17日 11:13
 * @Version 1.0
 */
public class demo {

    static HashMap map = new HashMap();
    static {
        map.put("dbType","MYSQL");
        map.put("driverClass","com.mysql.cj.jdbc.Driver");
        map.put("url","jdbc:mysql://152.136.154.249:3306/demo?&allowMultiQueries=true&characterEncoding=utf-8");
        map.put("user","demo");
        map.put("password","kKJ8XynXLzjfYDA7");
    }

    static  DataSourceProviderEntity source = DataSourceProviderEntity.builder()
            .type("JDBC")
            .name("DEMO")
            .sourceId("1")
            .properties(map)
            .build();


    static OuterDataSourceManager outerDataSourceManager = new OuterDataSourceManager();

    public static void main(String[] args) throws Exception {

        ResponseBody responseBody = outerDataSourceManager.testConnection(source);
        System.out.println(responseBody);
    }
}
