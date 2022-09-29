package com.cn.jmw.service;

import com.cn.jmw.data.source.pojo.DataProviderSource;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月29日 17:23
 * @Version 1.0
 */
public interface ConnectionDataSourceService {

    public Object testConnection(DataProviderSource source) throws Exception;

}
