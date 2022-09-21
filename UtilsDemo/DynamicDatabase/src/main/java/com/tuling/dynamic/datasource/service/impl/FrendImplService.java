package com.tuling.dynamic.datasource.service.impl;

import com.tuling.dynamic.datasource.annotation.WR;
import com.tuling.dynamic.datasource.mapper.FrendMapper;
import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.service.FrendService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
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
