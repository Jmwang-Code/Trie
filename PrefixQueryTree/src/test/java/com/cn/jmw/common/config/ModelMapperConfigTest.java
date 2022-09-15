package com.cn.jmw.common.config;

import com.cn.jmw.utils.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ModelMapperConfigTest {

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void modelMapperConfigTest() {
        ModelMapperConfig.ModelPre modelPre = new ModelMapperConfig.ModelPre();
        modelPre.setPre(1);
        modelPre.setAfter(1);
        modelPre.setId(1);
        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.ModelMapper();
        ModelMapperConfig.ModelAfter map = modelMapper.map(modelPre, ModelMapperConfig.ModelAfter.class);
        System.out.println(map);
    }

    @Test
    public void redisTest(){
        redisUtils.set("AMD","YYDS");
        System.out.println(redisUtils.get("AMD"));
    }
}