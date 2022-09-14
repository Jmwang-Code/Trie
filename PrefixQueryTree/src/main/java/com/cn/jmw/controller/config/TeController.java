package com.cn.jmw.controller.config;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月14日 12:57
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.jmw.pojo.config.TrieConfig;
import com.cn.jmw.utils.config.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeController {
    @Autowired
    private TrieConfig trieConfig;

    @GetMapping(value = "/T",produces = "application/json")
    public String test(){
        return ConfigUtils.getFormatJSON(trieConfig);
    }
}
