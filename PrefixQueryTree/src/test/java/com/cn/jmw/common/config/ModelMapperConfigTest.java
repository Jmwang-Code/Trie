package com.cn.jmw.common.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class ModelMapperConfigTest {

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
}