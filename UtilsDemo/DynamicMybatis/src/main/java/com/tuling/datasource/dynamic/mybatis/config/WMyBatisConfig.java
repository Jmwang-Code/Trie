package com.tuling.datasource.dynamic.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 写数据源配置
 */
@Configuration
// 继承mybatis:
// 1. 指定扫描的mapper接口包（主库）
// 2. 指定使用sqlSessionFactory是哪个（主库）
@MapperScan(basePackages = "com.tuling.datasource.dynamic.mybatis.mapper.w",
        sqlSessionFactoryRef="wSqlSessionFactory")
public class WMyBatisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource1")
    public DataSource dataSource1() {
        // 底层会自动拿到spring.datasource中的配置， 创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory wSqlSessionFactory()
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 指定主库
        sessionFactory.setDataSource(dataSource1());
        // 指定主库对应的mapper.xml文件
        /*sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/order/*.xml"));*/
        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager wTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource1());
        return dataSourceTransactionManager;
    }


    @Bean
    public TransactionTemplate wTransactionTemplate(){
        return new TransactionTemplate(wTransactionManager());
    }
}
