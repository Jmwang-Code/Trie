package com.cn.jmw.tree;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 王佳
 */
@SpringBootApplication
@Slf4j
public class BinaryTreeApplication {
    @Autowired
    @Qualifier("mysqlDB")
    private DataSource mysqlDB;

    public static void main(String[] args) throws UnknownHostException {
        //https://blog.csdn.net/qq_40998237/article/details/125885017
        ConfigurableApplicationContext application = SpringApplication.run(BinaryTreeApplication.class, args);
    }

    void helloDB(){
        try {
            Connection connection = mysqlDB.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("show tables");
            ResultSet resultSet = statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
