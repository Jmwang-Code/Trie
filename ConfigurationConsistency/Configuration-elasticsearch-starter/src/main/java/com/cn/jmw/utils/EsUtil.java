package com.cn.jmw.utils;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cn.jmw.constant.EsConstant.*;

/**
 * es 7.x 工具类
 * @author zhuwukai
 * @date 2021.11.18
 */
@Slf4j
@Component
public class EsUtil {

    @Resource
    private RestHighLevelClient restHighLevelClient;


    /**
     * 关键字
     */
    public static final String KEYWORD = ".keyword";
    /**
     * 判断索引是否存在
     */
    public boolean checkIndex(String indexName) {
        try {
            return restHighLevelClient.indices().exists(new GetIndexRequest(indexName), DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * 创建索引
     */
    public boolean createIndex(String indexName, Map<String, ?> mapping) {
        return createIndex(indexName, null, null, mapping);
    }

    /**
     * 创建索引
     */
    public boolean createIndex(String indexName, Map<String, ?> aliases, Map<String, ?> settings, Map<String, ?> properties) {
        if (checkIndex(indexName)) {
            return Boolean.TRUE;
        }
        try {
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            if (aliases != null && aliases.size() > 0) {
                request.aliases(aliases);
            }
            if (settings != null && settings.size() > 0) {
                request.settings(settings);
            }
            if (properties != null && properties.size() > 0) {
                Map<String, Object> mapping = new HashMap<>();
                mapping.put(INDEX_MAPPING_PROPERTIES, properties);
                // 禁止自动添加字段
                mapping.put(INDEX_MAPPING_DYNAMIC, INDEX_MAPPING_DYNAMIC_STRICT);
                request.mapping(mapping);
            }
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, DEFAULT_OPTIONS);
            log.info("createIndexResponse:{}", createIndexResponse);
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
    /**
     * 删除索引
     *
     * @param index
     * @return
     */
    public boolean deleteIndex(String index) throws IOException {
        if(!checkIndex(index)) {
            log.error("Index is not exits!");
            return false;
        }
        //删除索引请求
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        //执行客户端请求
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

        log.info("删除索引{}成功",index);

        return delete.isAcknowledged();
    }
    /**
     * 写入数据
     */
    public boolean insert(String indexName, String source,String id) {
        try {
            // opType:create：id不能为空，必须指定id，id相同时报错
            // opType:index：id可以为空，不指定id时自动生成，id相同时覆盖
            IndexRequest request = new IndexRequest(indexName).id(id).opType(DocWriteRequest.OpType.CREATE).source(source, XContentType.JSON);

            IndexResponse indexResponse = restHighLevelClient.index(request, DEFAULT_OPTIONS);
            log.info("indexResponse:{}", indexResponse);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * 批量写入数据
     */
    public boolean batchInsert(String indexName, List<Map<String, Object>> sourceList) {
        try {
            BulkRequest request = new BulkRequest();
            for (Map<String, Object> source : sourceList) {
                request.add(new IndexRequest(indexName).id(source.get(ID).toString())
                        // opType:create：id不能为空，必须指定id，id相同时报错
                        // opType:index：id可以为空，不指定id时自动生成，id相同时覆盖
                        .opType(DocWriteRequest.OpType.CREATE).source(source));
            }
            BulkResponse bulkResponse = restHighLevelClient.bulk(request, DEFAULT_OPTIONS);
            log.info("bulkResponse:{}", bulkResponse);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * 更新数据，可以直接修改索引结构
     */
    public boolean update(String indexName, String source,String id) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(indexName, id);
            updateRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
            // 此处大坑 如果要使用Map方法, 入参必须为Map<String, Object>类型
            updateRequest.doc(source,XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, DEFAULT_OPTIONS);
            log.info("updateResponse:{}", updateResponse);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * 删除数据
     */
    public boolean delete(String indexName, String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, DEFAULT_OPTIONS);
            log.info("deleteResponse:{}", deleteResponse);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * 查询总数
     */
    public Long countAll(String indexName) {
        CountRequest countRequest = new CountRequest(indexName);
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        countRequest.query(queryBuilder);
        try {
            CountResponse countResponse = restHighLevelClient.count(countRequest, DEFAULT_OPTIONS);
            return countResponse.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 查询总数
     */
    public Long countMatches(String indexName,String heaCode) {
        CountRequest countRequest = new CountRequest(indexName);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("heaCode", heaCode));
        boolQueryBuilder.must(QueryBuilders.matchQuery("del", 0));
        log.info("boolQueryBuilder:{}", boolQueryBuilder);
        countRequest.query(boolQueryBuilder);
        try {
            CountResponse countResponse = restHighLevelClient.count(countRequest, DEFAULT_OPTIONS);
            return countResponse.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 分页查询
     */
    public List<Map<String, Object>> queryMatches(String indexName,SearchSourceBuilder searchSourceBuilder) {
//        // 查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页参数
//        searchSourceBuilder.from(pager.getStartPosition());
//        searchSourceBuilder.size(pager.getPageSize());
//        log.info("pager:{}, offset:{}", pager, pager.getStartPosition());
        // 过滤参数
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        matches.forEach((k, v) -> boolQueryBuilder.must(QueryBuilders.matchQuery(k, v)));
//        log.info("boolQueryBuilder:{}", boolQueryBuilder);
//        searchSourceBuilder.query(boolQueryBuilder);
        // 排序
//        searchSourceBuilder.sort("updatedDt", SortOrder.DESC);
//        searchSourceBuilder.sort("buildDate", SortOrder.ASC);
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResp = restHighLevelClient.search(searchRequest, DEFAULT_OPTIONS);
            List<Map<String, Object>> data = new ArrayList<>();
            SearchHit[] searchHitArr = searchResp.getHits().getHits();
            for (SearchHit searchHit : searchHitArr) {
                Map<String, Object> temp = searchHit.getSourceAsMap();
                data.add(temp);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * 通过ID判断文档是否存在
     * @param index  索引，类似数据库
     * @param id     数据ID
     * @return
     */
    public  boolean existsById(String index,String id) throws IOException {
        GetRequest request = new GetRequest(index, id);
        //不获取返回的_source的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        return restHighLevelClient.exists(request, RequestOptions.DEFAULT);
    }

}


