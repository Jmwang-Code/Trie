package com.cn.jmw.enums;

/**
 * @Author jmw
 * @Description
 * @Date 14:33 2022/9/6
 **/
public enum BusinessCodeEnum {

    DEPT("organization:%s:department:max_code", 7),
    FUNCTION("organization:%s:function:max_code", 7),
    MENU("organization:%s:menu:max_code", 7),

    PROJECT("organization:%s:project:max_code", 8);

    int codeLength;
    String key;

    BusinessCodeEnum(String key, int codeLength) {
        this.key = key;
        this.codeLength = codeLength;
    }

    public int CODE_LENGTH() {
        return codeLength;
    }

    public String getKey(Object value) {
        return String.format(key, value);
    }
}
