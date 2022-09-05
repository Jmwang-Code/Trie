//package com.cn.jmw.cache.config;//package com.sangu.cloud.cache.config;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//public class RedisConfiguration {
//    @Value("${spring.redis.cluster.nodes:}")
//    private String nodes;
////    @Value("${spring.redis.cache.host:}")
////    private String host;
//    @Value("${spring.redis.cluster.password:}")
//    private String password;
//    @Value("${spring.redis.cluster.maxIdle:}")
//    private Integer maxIdle;
//    @Value("${spring.redis.cluster.minIdle:}")
//    private Integer minIdle;
//    @Value("${spring.redis.cluster.maxTotal:}")
//    private Integer maxTotal;
//    @Value("${spring.redis.cluster.maxWaitMillis:}")
//    private Long maxWaitMillis;
//
//    @Bean
//    @Primary
//    LettuceConnectionFactory lettuceConnectionFactory() {
//        // 连接池配置
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxIdle(maxIdle == null ? 8 : maxIdle);
//        poolConfig.setMinIdle(minIdle == null ? 1 : minIdle);
//        poolConfig.setMaxTotal(maxTotal == null ? 8 : maxTotal);
//        poolConfig.setMaxWaitMillis(maxWaitMillis == null ? 5000L : maxWaitMillis);
//
//        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
//                .poolConfig(poolConfig)
//                .build();
//
//        // 集群redis
//        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
//        Set<RedisNode> nodeses = new HashSet<>();
//        String[] hostses = nodes.split(",");
//        for (String h : hostses) {
//            h = h.replaceAll("\\s", "").replaceAll("\n", "");
//            if (!"".equals(h)) {
//                String host = h.split(":")[0];
//                int port = Integer.valueOf(h.split(":")[1]);
//                nodeses.add(new RedisNode(host, port));
//            }
//        }
//        redisConfig.setClusterNodes(nodeses);
//        // 跨集群执行命令时要遵循的最大重定向数量
//        redisConfig.setMaxRedirects(3);
//        redisConfig.setPassword(password);
//
//        return new LettuceConnectionFactory(redisConfig, lettucePoolingClientConfiguration);
//    }
//
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
////        RedisTemplate<String, Object> template = new RedisTemplate<>();
////        template.setConnectionFactory(lettuceConnectionFactory);
////        //序列化类
////        MyRedisSerializer myRedisSerializer = new MyRedisSerializer();
////        //key序列化方式
////        template.setKeySerializer(myRedisSerializer);
////        //value序列化
////        template.setValueSerializer(myRedisSerializer);
////        //value hashmap序列化
////        template.setHashValueSerializer(myRedisSerializer);
////        return template;
////    }
//
//    static class MyRedisSerializer implements RedisSerializer<Object> {
//
//        @Override
//        public byte[] serialize(Object o) throws SerializationException {
//            return serializeObj(o);
//        }
//
//        @Override
//        public Object deserialize(byte[] bytes) throws SerializationException {
//            return deserializeObj(bytes);
//        }
//
//        /**
//         * 序列化
//         * @param object
//         * @return
//         */
//        private static byte[] serializeObj(Object object) {
//            ObjectOutputStream oos = null;
//            ByteArrayOutputStream baos = null;
//            try {
//                baos = new ByteArrayOutputStream();
//                oos = new ObjectOutputStream(baos);
//                oos.writeObject(object);
//                byte[] bytes = baos.toByteArray();
//                return bytes;
//            } catch (Exception e) {
//                throw new RuntimeException("序列化失败!", e);
//            }
//        }
//
//        /**
//         * 反序列化
//         * @param bytes
//         * @return
//         */
//        private static Object deserializeObj(byte[] bytes) {
//            if (bytes == null){
//                return null;
//            }
//            ByteArrayInputStream bais = null;
//            try {
//                bais = new ByteArrayInputStream(bytes);
//                ObjectInputStream ois = new ObjectInputStream(bais);
//                return ois.readObject();
//            } catch (Exception e) {
//                throw new RuntimeException("反序列化失败!", e);
//            }
//        }
//    }
//}