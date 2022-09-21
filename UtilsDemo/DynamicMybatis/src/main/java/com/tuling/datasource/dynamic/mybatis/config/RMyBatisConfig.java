package com.tuling.datasource.dynamic.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Configuration
// 继承mybatis:
// 1. 指定扫描的mapper接口包（主库）
// 2. 指定使用sqlSessionFactory是哪个（主库）
@MapperScan(basePackages = "com.tuling.datasource.dynamic.mybatis.mapper.r",
        sqlSessionFactoryRef="rSqlSessionFactory")
public class RMyBatisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource dataSource2() {
        // 底层会自动拿到spring.datasource中的配置， 创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory rSqlSessionFactory()
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 指定主库
        sessionFactory.setDataSource(dataSource2());
        // 指定主库对应的mapper.xml文件
        /*sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/r/*.xml"));*/
        return sessionFactory.getObject();
    }



    @Bean
    public DataSourceTransactionManager rTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource2());
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate rTransactionTemplate(){
        return new TransactionTemplate(rTransactionManager());
    }
}
