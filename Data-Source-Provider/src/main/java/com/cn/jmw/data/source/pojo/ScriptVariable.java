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

import com.cn.jmw.data.source.common.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @Author jmw
 * @Description 脚本变量 -> 权限变量和查询变量
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScriptVariable extends TypedValue {

    private String name;

    private VariableTypeEnum type;

    private Set<String> values;

    private String nameWithQuote;

    private boolean expression;

    // Permission variable valid flag, which is false when executed by the organization owner
    private boolean disabled;

    private String format;

    @Override
    public String toString() {
        if (values == null) {
            return "";
        }
        return String.join(",", values);
    }

    public ScriptVariable() {
    }

    /**
     * @Author jmw
     * @Description 初始化脚本变量等方法
     */
    public ScriptVariable(String name, VariableTypeEnum type, ValueType valueType, Set<String> values, boolean expression) {
        this.name = name;
        this.type = type;
        this.values = values;
        this.valueType = valueType;
        this.expression = expression;
    }

    public String getNameWithQuote() {
        if (nameWithQuote != null) {
            return nameWithQuote;
        }
        nameWithQuote = StringUtils.prependIfMissing(name, Const.DEFAULT_VARIABLE_QUOTE);
        nameWithQuote = StringUtils.appendIfMissing(nameWithQuote, Const.DEFAULT_VARIABLE_QUOTE);
        return nameWithQuote;
    }

    public String valueToString() {
        if (CollectionUtils.isEmpty(values)) {
            return "";
        }
        return String.join(",", values);
    }
}