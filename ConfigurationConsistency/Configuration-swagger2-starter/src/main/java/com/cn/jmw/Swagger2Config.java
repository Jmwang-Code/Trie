package com.cn.jmw;

import com.cn.jmw.uitls.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
        int envStrLength = envStr.length()+6;
        log.info("┌{}┐",getLogo(0,envStrLength));
        log.info("│{}initialization  Swagger2{}│",getLogo(2,envStrLength),getLogo(-1,envStrLength));
        log.info("│{}{}{}|",getLogo(1,envStrLength),envStr,getLogo(1,envStrLength));
        log.info("└{}┘",getLogo(0,envStrLength));
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }

    /**
     * @Author 写注释的暖男jmw
     * @Description 0首行和末行  1url 2默认字段
     * @Date 17:53 2022/9/6
     * 到时候封装一下放入公共模块
     */
    public String getLogo(int length,int size){
        StringBuilder stringBuilder = new StringBuilder();
        if (length==0)for (int i = 0; i < size; i++) stringBuilder.append("─");
        else if(length==1)for (int i = 0; i < 3; i++) stringBuilder.append(" ");
        else if(length==2)for (int i = 0; i < (size-23)/2; i++) stringBuilder.append(" ");
        else for (int i = 0; i < size-24-((size-23)/2); i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }
}
