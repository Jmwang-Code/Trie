package com.cn.jmw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author jmw
 */
@EnableAsync
@SpringBootApplication
public class PrefixQueryTreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrefixQueryTreeApplication.class, args);
    }

}
