package com.tuling.dynamic.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
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
    @DS("slave")  // 从库， 如果按照下划线命名方式配置多个  ， 可以指定前缀即可（组名）
    public List<Frend> list() {
        return frendMapper.list();
    }

    @Override
    @DS("master")
    public void save(Frend frend) {
        frendMapper.save(frend);
    }


    @DS("master")
    @DSTransactional
    public void saveAll(){
        // 执行多数据源的操作
    }

}
