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
package com.cn.jmw.data.source.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @Author jmw
 * @Description 数据源提供类
 * @Date 17:43 2022/9/29
 */
@Data
@ToString
public class DataProviderSource {

    /**
     * 数据源id
     */
    private String sourceId;

    /**
     * 类型比 如JDBC FILE HTTP KAFKA
     */
    private String type;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 链接数据源信息
     */
    private Map<String, Object> properties;

    /**
     * 自定义连接池信息
     */
    private List<ScriptVariable> variables;

}