package com.cn.jmw.uitls;

import com.cn.jmw.exception.BizException;
import com.cn.jmw.exception.code.ExceptionCode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 对jackJson进行封装
 * @author zhuwukai
 * @date 2021.11.11
 */
@Slf4j
public class JsonUtil {

    public static <T> String toJson(T value) {
        try {
            return getInstance().writeValueAsString(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static byte[] toJsonAsBytes(Object object) {
        try {
            return getInstance().writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static <T> T parse(String content, Class<T> valueType) {
        try {
            return getInstance().readValue(content, valueType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T parse(String content, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(content, typeReference);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static <T> T parse(byte[] bytes, Class<T> valueType) {
        try {
            return getInstance().readValue(bytes, valueType);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static <T> T parse(byte[] bytes, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(bytes, typeReference);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static <T> T parse(InputStream in, Class<T> valueType) {
        try {
            return getInstance().readValue(in, valueType);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static <T> T parse(InputStream in, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(in, typeReference);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static Map<String, Object> toMap(String content) {
        try {
            return getInstance().readValue(content, Map.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> Map<String, T> toMap(String content, Class<T> valueTypeRef) {
        try {
            Map<String, Map<String, Object>> map = getInstance().readValue(content, new TypeReference<Map<String, Map<String, Object>>>() {
            });
            Map<String, T> result = new HashMap<>(map.size());
            map.forEach((key, value) -> {
                result.put(key, toPojo(value, valueTypeRef));
            });

            return result;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toPojo(Map fromValue, Class<T> toValueType) {
        return getInstance().convertValue(fromValue, toValueType);
    }

    public static <T> T toPojo(JsonNode resultNode, Class<T> toValueType) {
        return getInstance().convertValue(resultNode, toValueType);
    }

    public static JsonNode readTree(String jsonString) {
        try {
            return getInstance().readTree(jsonString);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static JsonNode readTree(InputStream in) {
        try {
            return getInstance().readTree(in);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static JsonNode readTree(byte[] content) {
        try {
            return getInstance().readTree(content);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static JsonNode readTree(JsonParser jsonParser) {
        try {
            return getInstance().readTree(jsonParser);
        } catch (IOException e) {
            throw new BizException(ExceptionCode.JSON_PARSE_ERROR.getCode(), e);
        }
    }

    public static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    private static class JacksonHolder {
        private final static ObjectMapper INSTANCE = new JacksonObjectMapper();
    }

    public static class JacksonObjectMapper extends ObjectMapper {
        private static final long serialVersionUID = 1L;

        public JacksonObjectMapper() {
            super();
            // 参考BaseConfig
            super.setLocale(Locale.CHINA)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                    .setDateFormat(new SimpleDateFormat(DateUtils.DEFAULT_DATE_TIME_FORMAT, Locale.CHINA))
                    .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
                    .configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true)
                    .findAndRegisterModules()
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                    .getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            super.registerModule(new MyJacksonModule());
            super.findAndRegisterModules();
        }

        @Override
        public ObjectMapper copy() {
            return super.copy();
        }
    }

}
