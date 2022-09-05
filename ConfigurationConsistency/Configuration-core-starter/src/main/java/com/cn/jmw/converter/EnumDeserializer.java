package com.cn.jmw.converter;

import com.cn.jmw.uitls.StrPool;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static com.cn.jmw.converter.EnumSerializer.ALL_ENUM_KEY_FIELD;

/**
 * enum反序列化工具
 * @author zhuwukai
 * @date 2021.11.11
 */
@Slf4j
public class EnumDeserializer extends StdDeserializer<Enum<?>> {
    public final static EnumDeserializer INSTANCE = new EnumDeserializer();


    public EnumDeserializer() {
        super(Enum.class);
    }

    @Override
    public Enum<?> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            // 读取
            JsonNode node = jp.getCodec().readTree(jp);
            // 当前字段
            String currentName = jp.currentName();
            // 当前对象
            Object currentValue = jp.getCurrentValue();
            // 在对象中找到改字段
            Class findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
            JsonNode code = node.get(ALL_ENUM_KEY_FIELD);
            String val = code != null ? code.asText() : node.asText();
            if (StringUtils.isEmpty(val) || StrPool.NULL.equals(val)) {
                return null;
            }
            return Enum.valueOf(findPropertyType, val);
        } catch (Exception e) {
            log.warn("解析枚举失败", e);
            return null;
        }
    }


}
