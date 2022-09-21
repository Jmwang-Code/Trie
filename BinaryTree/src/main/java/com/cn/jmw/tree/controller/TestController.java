package com.cn.jmw.tree.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.jmw.tree.bean.TreeNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月15日 16:40
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping(value = "T",produces = "application/json")
    public String get(){
        List list = new ArrayList();
        list.add(new TreeNode(1));
        list.add(new TreeNode(4));
        list.add(new TreeNode(5));
        list.add(new TreeNode(1324));
        return new JSONArray(list).toJSONString();
    }
}
