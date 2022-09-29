package com.cn.jmw.data.source.service;

import com.cn.jmw.data.source.pojo.DataProviderSource;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月29日 18:16
 * @Version 1.0
 */
public interface OuterProviderManager {

    /**
     * @Author jmw
     * @Description 测试链接器
     * @param source:
     * @return Object
     * @Date 18:06 2022/9/29
     */
    Object testConnection(DataProviderSource source) throws Exception;
}
