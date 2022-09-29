package com.cn.jmw.data.source.pojo;

public enum ValueType {

    STRING,

    NUMERIC,

    DATE,

    BOOLEAN,

    IDENTIFIER,
    //  do nothing
    FRAGMENT,
    //  will be parse to sql node
    SNIPPET,

    KEYWORD,
}
