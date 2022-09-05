package com.cn.jmw.constant;

import java.util.HashMap;

import static com.cn.jmw.constant.EsConstant.DATE_FORMAT;
import static com.cn.jmw.constant.EsConstant.DATE_FORMAT_PATTERN;

/**
 * @author zhuwukai
 * @date 2021.11.18
 */

public class MappingProperty extends HashMap<String, Object> {
    /**
     * 1:支持分词，全文检索,支持模糊、精确查询,不支持聚合,排序操作;
     * 2:test类型的最大支持的字符长度无限制,适合大字段存储；
     * 使用场景：
     * 存储全文搜索数据, 例如: 邮箱内容、地址、代码块、博客文章内容等。
     * 默认结合standard analyzer(标准解析器)对文本进行分词、倒排索引。
     * 默认结合标准分析器进行词命中、词频相关度打分。
     */
    public static final String TEXT = "text";
    /**
     * 1:不进行分词，直接索引,支持模糊、支持精确匹配，支持聚合、排序操作。
     * 2:keyword类型的最大支持的长度为——32766个UTF-8类型的字符,可以通过设置ignore_above指定自持字符长度，超过给定长度后的数据将不被索引，无法通过term精确匹配检索返回结果。
     * <p>
     * 使用场景：
     * 存储邮箱号码、url、name、title，手机号码、主机名、状态码、邮政编码、标签、年龄、性别等数据。
     * 用于筛选数据(例如: select * from x where status='open')、排序、聚合(统计)。
     * 直接将完整的文本保存到倒排索引中。
     */
    public static final String KEYWORD = "keyword";

    public static final String BYTE = "byte";

    public static final String SHORT = "short";

    public static final String INTEGER = "integer";

    public static final String LONG = "long";

    public static final String FLOAT = "float";

    public static final String HALF_FLOAT = "half_float";

    public static final String SCALED_FLOAT = "scaled_float";

    public static final String DOUBLE = "double";

    public static final String DATE = "date";

    public static final String INTEGER_RANGE = "integer_range";

    public static final String LONG_RANGE = "long_range";

    public static final String FLOAT_RANGE = "float_range";

    public static final String DOUBLE_RANGE = "double_range";

    public static final String DATE_RANGE = "date_range";

    public static final String BOOLEAN = "boolean";

    public static final String BINARY = "binary";

    public static final String OBJECT = "object";

    public static final String IP = "ip";

    public static final String NESTED = "nested";

    /**
     * 如果想要在创建索引和查询时分别使用不同的分词器，ElasticSearch也是支持的。
     * <p>
     * 在创建索引，指定analyzer，ES在创建时会先检查是否设置了analyzer字段，如果没定义就用ES预设的
     * <p>
     * 在查询时，指定search_analyzer，ES查询时会先检查是否设置了search_analyzer字段，
     * 如果没有设置，还会去检查创建索引时是否指定了analyzer，还是没有还设置才会去使用ES预设的
     * <p>
     * ES分析器主要有两种情况会被使用：
     * <p>
     * 插入文档时，将text类型的字段做分词然后插入倒排索引，此时就可能用到analyzer指定的分词器
     * 在查询时，先对要查询的text类型的输入做分词，再去倒排索引搜索，此时就可能用到search_analyzer指定的分词器
     */
    public static final String ANALYZER = "analyzer";

    public static final String SEARCH_ANALYZER = "search_analyzer";
    /**
     * 会做最粗粒度的拆分，比如会将“中华人民共和国”拆分为中华人民共和国
     */
    public static final String ANALYZER_IK_SMART = "ik_smart";

    /**
     * 会将文本做最细粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为“中华人民共和国、中华人民、中华、华人、人民共和国、人民、共和国等词语
     */
    public static final String ANALYZER_IK_MAX_WORD = "ik_max_word";


    public MappingProperty() {
    }

    public MappingProperty(String type) {
        this.put("type", type);
        // 日期格式处理
        if (DATE.equals(type)) {
            this.put(DATE_FORMAT, DATE_FORMAT_PATTERN);
        } else if (TEXT.equals(type)) {
            // text类型字段使用IK分词器
            this.put(ANALYZER, ANALYZER_IK_MAX_WORD);
            this.put(SEARCH_ANALYZER, ANALYZER_IK_MAX_WORD);
        }
    }
}