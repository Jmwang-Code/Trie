package com.cn.jmw.service.impl;

import com.cn.jmw.service.FrendService;
import com.cn.jmw.annotation.WR;
import com.cn.jmw.mapper.FrendMapper;
import com.cn.jmw.entity.Frend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrendImplService implements FrendService {

    @Autowired
    FrendMapper frendMapper;


    @Override
    @WR("R")        // 库2
    public List<Frend> list() {
        return frendMapper.list();
    }

    @Override
    @WR("W")        // 库1
    public void save(Frend frend) {
        frendMapper.save(frend);
    }

}
