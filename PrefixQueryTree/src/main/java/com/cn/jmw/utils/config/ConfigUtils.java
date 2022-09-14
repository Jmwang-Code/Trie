package com.cn.jmw.utils.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月14日 15:52
 * @Version 1.0
 */
public class ConfigUtils {

    public static String getFormatJSON(Object o){
        String jsonStr = JSON.toJSONString(o);
        JSONObject object = JSONObject.parseObject(jsonStr);
        String pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        return pretty;
    }
}
