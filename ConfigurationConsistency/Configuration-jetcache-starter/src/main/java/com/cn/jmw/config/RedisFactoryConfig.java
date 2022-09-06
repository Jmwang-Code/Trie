//package com.cn.jmw.cache.config;//package com.sangu.cloud.cache.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.env.MapPropertySource;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class RedisFactoryConfig {
//
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public RedisConnectionFactory myLettuceConnectionFactory() {
//        Map<String, Object> source = new HashMap<String, Object>(3);
//        source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
//        source.put("spring.redis.cluster.timeout", environment.getProperty("spring.redis.cluster.timeout"));
//        source.put("spring.redis.cluster.password", environment.getProperty("spring.redis.cluster.password"));
//        source.put("spring.redis.cluster.max-redirects", environment.getProperty("spring.redis.cluster.max-redirects"));
//        RedisClusterConfiguration redisClusterConfiguration;
//        redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
//        return new LettuceConnectionFactory(redisClusterConfiguration);
//    }
//}