package com.cn.jmw.controller;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.cn.jmw.entity.Frend;
import com.cn.jmw.service.FrendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("frend")
@Slf4j
public class FrendController {

    @Autowired
    private FrendService frendService;

    @GetMapping(value = "select")
    public List<Frend> select(){
        return frendService.list();
    }


    @GetMapping(value = "insert")
    public void in(){
        Frend frend = new Frend();
        frend.setName("徐庶");
        frendService.save(frend);
    }

    @Autowired
    private DataSource dataSource;

    @DeleteMapping("ds/del")
    public String remove(String name) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.removeDataSource(name);

        return "删除成功";
    }



}
