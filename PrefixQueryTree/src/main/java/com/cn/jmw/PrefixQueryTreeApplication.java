package com.cn.jmw;

import com.cn.jmw.common.annotation.EnableOkHttp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

}
