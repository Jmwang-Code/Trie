package com.cn.jmw.data.source.service;

import com.cn.jmw.data.source.exception.Exceptions;
import com.cn.jmw.data.source.pojo.DataProviderSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月29日 18:17
 * @Version 1.0
 */
@Slf4j
@Service
public class OuterProviderManagerImpl implements OuterProviderManager{

    //使用调表hash进行缓存
    private static final Map<String, DataProvider> cachedDataProviders = new ConcurrentSkipListMap<>();

    /**
     * @Author jmw
     * @Description 测试链接
     * @param source:
     * @return Object
     * @Date 18:10 2022/9/29
     */
    @Override
    public Object testConnection(DataProviderSource source) throws Exception {
        return getDataProviderService(source.getType()).test(source);
    }

    /**
     * @Author jmw
     * @Description 通过数据源类型获取 对应数据提供者
     * @param type: 类型比如JDBC HTTP FILE KAFKA
     * @return DataProvider
     * @Date 18:12 2022/9/29
     */
    private DataProvider getDataProviderService(String type) {
        if (cachedDataProviders.size() == 0) {
            loadDataProviders();
        }
        DataProvider dataProvider = cachedDataProviders.get(type);
        if (dataProvider == null) {
            Exceptions.msg("No data provider type " + type);
        }
        return dataProvider;
    }

    private void loadDataProviders() {
        ServiceLoader<DataProvider> load = ServiceLoader.load(DataProvider.class);
        for (DataProvider dataProvider : load) {
            try {
                cachedDataProviders.put(dataProvider.getType(), dataProvider);
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

}
