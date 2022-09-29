package com.cn.jmw.service.impl;

import com.cn.jmw.data.source.common.Const;
import com.cn.jmw.data.source.pojo.DataProviderSource;
import com.cn.jmw.data.source.service.OuterProviderManager;
import com.cn.jmw.service.ConnectionDataSourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月29日 17:23
 * @Version 1.0
 */
@Service
public class ConnectionDataSourceServiceImpl implements ConnectionDataSourceService {

    @Autowired
    private OuterProviderManager outerProviderManager;
    @Override
    public Object testConnection(DataProviderSource source) throws Exception {
        Map<String, Object> properties = source.getProperties();
        if (!CollectionUtils.isEmpty(properties)) {
            for (String key : properties.keySet()) {
                Object val = properties.get(key);
                if (val instanceof String) {
                    //未加密
                    properties.put(key, val.toString());
                }
            }
        }
        return outerProviderManager.testConnection(source);
    }

}
