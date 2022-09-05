package com.cn.jmw.constant;

import org.elasticsearch.client.RequestOptions;

/**
 * @author zhuwukai
 * @date 2021.11.18
 */

public class EsConstant {

    public static final RequestOptions DEFAULT_OPTIONS = RequestOptions.DEFAULT;

    public static final String ID = "@@id";

    public static final String INDEX_MAPPING_PROPERTIES = "properties";

    public static final String INDEX_MAPPING_DYNAMIC = "dynamic";

    public static final String INDEX_MAPPING_DYNAMIC_STRICT = "strict";

    public static final String DATE_FORMAT = "format";

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || yyyy/MM/dd HH:mm:ss|| yyyy/MM/dd ||epoch_millis";

    private EsConstant() {

    }
}