package com.cn.jmw.uitls;

import com.cn.jmw.base.BaseEnum;
import com.cn.jmw.converter.EnumDeserializer;
import com.cn.jmw.converter.EnumSerializer;
import com.cn.jmw.converter.MyLocalDateTimeDeserializer;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * jackson 自定义序列化 & 反序列化 规则
 *
 * @author zhuwukai
 * @date 2021.11.11
 */
public class MyJacksonModule extends SimpleModule {

    public MyJacksonModule() {
        super(PackageVersion.VERSION);
        this.addDeserializer(LocalDateTime.class, MyLocalDateTimeDeserializer.INSTANCE);
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_FORMAT)));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_TIME_FORMAT)));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
        this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_FORMAT)));
        this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_TIME_FORMAT)));
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addSerializer(BigInteger.class, ToStringSerializer.instance);
        this.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        this.addSerializer(BaseEnum.class, EnumSerializer.INSTANCE);
        this.addDeserializer(Enum.class, EnumDeserializer.INSTANCE);
    }
}
