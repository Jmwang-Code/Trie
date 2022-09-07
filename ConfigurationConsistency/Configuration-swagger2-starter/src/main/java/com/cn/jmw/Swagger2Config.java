package com.cn.jmw;

import com.cn.jmw.uitls.SpringUtils;
import com.cn.jmw.utils.StartLogPrinting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月30日 12:49
 * @Version 1.0
 */
@Configuration
@Slf4j
public class Swagger2Config {
//😂
    /**
     * Spring 工具类
     */
    @Bean
    @DependsOn(value = "createRestApi")
    public SpringUtils getSwagger2Config(ApplicationContext applicationContext, ConfigurableApplicationContext application) throws UnknownHostException {
        Environment env = applicationContext.getEnvironment();
        String envStr = "http://"+InetAddress.getLocalHost().getHostAddress()+":"+ env.getProperty("server.port")+"/doc.html";
        StartLogPrinting.SingletonEnum.SINGLETON.getInstance()
                .add("initialization  Swagger2")
                .add(envStr)
                .build();
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }

}
