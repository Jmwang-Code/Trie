package com.cn.jmw.data.source.factory;

import com.cn.jmw.data.source.common.AbstractFactoryEnum;
import com.cn.jmw.data.source.exception.Exceptions;
import com.cn.jmw.data.source.factory.adapters.JdbcDataSourceAdapter;
import com.cn.jmw.data.source.pojo.DataProviderSource;
import com.cn.jmw.data.source.pojo.JdbcDriverInfo;
import com.cn.jmw.data.source.pojo.JdbcProperties;
import com.cn.jmw.data.source.service.DataProvider;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

@Slf4j
/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:35
 * @Version 1.0
 */
public class DataSourceFactoryProvider extends DataProvider {

    public static final String DEFAULT_ADAPTER = "datart.data.provider.jdbc.adapters.JdbcDataProviderAdapter";

    private static final String JDBC_DRIVER_BUILD_IN = "/jdbc-driver.yml";

    private static final String JDBC_DRIVER_EXT = "config/jdbc-driver-ext.yml";

    public static final String DB_TYPE = "dbType";

    public static final String USER = "user";

    public static final String PASSWORD = "password";

    public static final String URL = "url";

    public static final String DRIVER_CLASS = "driverClass";

    public static final String ENABLE_SPECIAL_SQL = "enableSpecialSQL";

    private static final String I18N_PREFIX = "config.template.jdbc.";

    /**
     * 获取连接时最大等待时间（毫秒）
     */
    public static final Integer DEFAULT_MAX_WAIT = 5000;


    /**
     * @Author jmw
     * @Description 动态创建不同工厂
     * @param choice:
     * @return AbstractFactory<DataSource>
     * @Date 17:08 2022/9/28
     */
    public static AbstractFactory<? extends DataSource> getFactory(AbstractFactoryEnum choice) {
        if (AbstractFactoryEnum.DRUID.equals(choice)) {
            return new DruidSourceFactory();
        }else if (AbstractFactoryEnum.HIKARI.equals(choice)){
            return new HikariSourceFactory<>();
        }
        return null;
    }


    /**
     * @Author jmw
     * @Description 直接使用创建对应工厂不推荐
     * @param :
     * @return DataSourceFactory<DataSource>
     * @Date 17:08 2022/9/28
     */
    @Deprecated
    public static DruidSourceFactory<? extends DataSource> getJDBCFactory() {
        return new DruidSourceFactory();
    }

    @Override
    public Object test(DataProviderSource source) throws Exception {
        return null;
    }

    @Override
    public String getConfigFile() {
        return "jdbc-data-provider.json";
    }

    @Override
    public void close() throws IOException {

    }




}
