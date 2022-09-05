package com.cn.jmw.converter;

import com.cn.jmw.uitls.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解决入参为 Date类型
 *
 * @author zhuwukai
 * @date 2021.11.11
 */
public class String2LocalDateTimeConverter extends BaseDateConverter<LocalDateTime> implements Converter<String, LocalDateTime> {

    protected static final Map<String, String> FORMAT = new LinkedHashMap(2);

    static {
        FORMAT.put(DateUtils.DEFAULT_DATE_TIME_FORMAT, DateUtils.DEFAULT_DATE_TIME_FORMAT_MATCHES);
        FORMAT.put(DateUtils.SLASH_DATE_TIME_FORMAT, DateUtils.SLASH_DATE_TIME_FORMAT_MATCHES);
        FORMAT.put(DateUtils.DEFAULT_DATE_TIME_FORMAT_EN, DateUtils.DEFAULT_DATE_TIME_FORMAT_EN_MATCHES);
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public LocalDateTime convert(String source) {
        return super.convert(source, (key) -> LocalDateTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
