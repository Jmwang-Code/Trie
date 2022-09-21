package com.tuling.datasource.dynamic.mybatis.controller;

import com.tuling.datasource.dynamic.mybatis.entity.Frend;
import com.tuling.datasource.dynamic.mybatis.service.FrendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
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


    @GetMapping(value = "save")
    public void save() throws Exception {
        Frend frend = new Frend();
        frend.setName("徐庶");
        frendService.saveAll(frend);
    }
}
