package com.cn.jmw.controller;

import com.cn.jmw.data.source.pojo.DataProviderSource;
import com.cn.jmw.data.source.response.ResponseData;
import com.cn.jmw.service.ConnectionDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月29日 17:22
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/test")
public class ConnectionDataSourceController {

    @Autowired
    private ConnectionDataSourceService connectionDataSourceService;

    @PostMapping(value = "/test")
    public ResponseData<Object> testConnection(@RequestBody DataProviderSource config) throws Exception {
        return ResponseData.success(connectionDataSourceService.testConnection(config));
    }

}
