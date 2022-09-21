package com.tuling.dynamic.datasource.controller;

import com.tuling.dynamic.datasource.DynamicDataSource;
import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.service.FrendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

}
