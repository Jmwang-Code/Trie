package com.tuling.datasource.dynamic.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement  // 开启事务
public class DynamicMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicMybatisApplication.class, args);
    }

}
