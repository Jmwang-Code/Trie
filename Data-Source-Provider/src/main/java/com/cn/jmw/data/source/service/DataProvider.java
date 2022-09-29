/*
 * Datart
 * <p>
 * Copyright 2021
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cn.jmw.data.source.service;

import com.cn.jmw.data.source.common.AutoCloseBean;
import com.cn.jmw.data.source.pojo.DataProviderConfigTemplate;
import com.cn.jmw.data.source.pojo.DataProviderInfo;
import com.cn.jmw.data.source.pojo.DataProviderSource;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public abstract class DataProvider extends AutoCloseBean {

    static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    /**
     * 测试DataProvider的连接。连接成功返回true，否则返回false。
     * <p>
     * Test the connection to the data source.Returns true on success of the connection or false on failure.
     *
     * @param source 一个Json字符串
     * @return Returns true on success of the connection or false on failure.
     */
    public abstract Object test(DataProviderSource source) throws Exception;

    /**
     * 返回DataProvider的type，type的值由实现者定义。
     * 这个type值作为DataProvider的唯一标识，必须是全局唯一的。
     * <p>
     * Returns the dataProvider's type.The value of type is defined by the implementation.
     * This type, as the unique identifier of the DataProvider, must be globally unique.
     *
     * @return dataProvider's type
     */
    public String getType() throws IOException {
        return getBaseInfo().getType();
    }

    public DataProviderInfo getBaseInfo() throws IOException {
        DataProviderConfigTemplate template = getConfigTemplate();
        DataProviderInfo dataProviderInfo = new DataProviderInfo();
        dataProviderInfo.setName(template.getName());
        dataProviderInfo.setType(template.getType());
        return dataProviderInfo;
    }

    /**
     * 读取DataProvider的配置模板，配置模板的信息是创建这个DataProvider实例时所需的信息。
     * <p>
     * Read the configuration template of the DataProvider. The configuration template information is needed to create the instance of the DataProvider.
     * This template is configured by the DataProvider according to the information it needs and stored in JSON format.The default save path is classpath:/data-provider.json
     *
     * @return 配置模板
     * @throws IOException 配置文件不存在或格式错误时抛出异常
     */
    public DataProviderConfigTemplate getConfigTemplate() throws IOException {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(getConfigFile())) {
            return MAPPER.readValue(resourceAsStream, DataProviderConfigTemplate.class);
        }
    }

    public abstract String getConfigFile();


    @Override
    public int timeoutMillis() {
        return Integer.MAX_VALUE;
    }

}