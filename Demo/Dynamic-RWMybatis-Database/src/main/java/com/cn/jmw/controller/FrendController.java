package com.cn.jmw.controller;

import com.cn.jmw.service.FrendService;
import com.cn.jmw.entity.Frend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping(value = "save")
    public void save() throws Exception {
        Frend frend = new Frend();
        frend.setName("徐庶");
        frendService.saveAll(frend);
    }
}
