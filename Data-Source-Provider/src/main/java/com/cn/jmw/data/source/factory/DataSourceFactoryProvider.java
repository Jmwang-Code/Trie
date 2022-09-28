package com.cn.jmw.data.source.factory;

import com.cn.jmw.data.source.pojo.common.AbstractFactoryEnum;

import javax.sql.DataSource;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月28日 14:35
 * @Version 1.0
 */
public class DataSourceFactoryProvider {

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

}
