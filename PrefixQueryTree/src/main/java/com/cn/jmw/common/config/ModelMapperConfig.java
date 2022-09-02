package com.cn.jmw.common.config;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author jmw
 * @Description 映射转换器
 * @date 2022年09月02日 16:43
 * @Version 1.0
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    @Scope(value = "singleton")
    public ModelMapper ModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //增添逻辑
        modelMapper.addMappings(StreamReqMap());
        modelMapper.validate();
        return modelMapper;
    }

    /**
     * @return org.modelmapper.PropertyMap<StreamReq4_0UAT, StreamReq>
     * @Author jmw
     * @Description 映射配置
     * @Date 17:02 2022/9/2
     * @Param []
     **/
    PropertyMap<ModelPre, ModelAfter> StreamReqMap() {
        return new PropertyMap<ModelPre, ModelAfter>() {
            @Override
            protected void configure() {
                skip(destination.getAfter());
            }
        };
    }

    @Data
    static
    class ModelPre {
        int id;
        int pre;
        int after;
    }

    @Data
    static
    class ModelAfter {
        int id;
        int pre;
        int after;
    }

}



