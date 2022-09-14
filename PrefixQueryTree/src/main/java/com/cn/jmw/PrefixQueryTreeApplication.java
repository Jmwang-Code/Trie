package com.cn.jmw;

import com.cn.jmw.common.annotation.EnableOkHttp;
import com.cn.jmw.pojo.config.TrieConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author jmw
 */
@EnableAsync
@EnableOkHttp
@SpringBootApplication
public class PrefixQueryTreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrefixQueryTreeApplication.class, args);
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//        c.setIgnoreUnresolvablePlaceholders(true);
//        return c;
//    }

}
