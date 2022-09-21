package com.tuling.datasource.dynamic.mybatis.service.impl;

import com.tuling.datasource.dynamic.mybatis.entity.Frend;
import com.tuling.datasource.dynamic.mybatis.mapper.r.RFrendMapper;
import com.tuling.datasource.dynamic.mybatis.mapper.w.WFrendMapper;
import com.tuling.datasource.dynamic.mybatis.service.FrendService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class FrendImplService implements FrendService {

    @Autowired
    private RFrendMapper rFrendMapper;

    @Autowired
    private WFrendMapper wFrendMapper;

    @Autowired
    TransactionTemplate wTransactionTemplate;
    @Autowired
    TransactionTemplate rTransactionTemplate;


    // 读-- 读库
    @Override
    public List<Frend> list() {
        return rFrendMapper.list();
    }

    // 保存-- 写库
    @Override
    public void save(Frend frend) {
        wFrendMapper.save(frend);
    }


    // 保存-- 写库
    @Override
    public void saveW(Frend frend) {
        frend.setName("徐庶W");
        wFrendMapper.save(frend);
    }

    // 保存-- 读库
    @Override
    public void saveR(Frend frend) {
        frend.setName("徐庶R");
        rFrendMapper.save(frend);
    }

    // 读-- 写库
    // 这种方式只能针对一个事务管理器进行控制，  无法完成多数据源控制
    // 特殊情况：
    //  1. 如果主库出现异常    可以
    //  2. 从库出现了异常   可以
    //  3. 异常出现在  主库和从库中间  可以
    //  除非在主库和从库之后进行的业务出现异常就不能保证事务一致性
    /*@Transactional(transactionManager = "wTransactionManager")
    @Transactional(transactionManager = "rTransactionManager")*/
    public void saveAll1(Frend frend) {
        saveW(frend);
        saveR(frend);
        int a = 1 / 0;
    }


    public void saveAll2(Frend frend) {
        wTransactionTemplate.execute((wstatus) -> {
            rTransactionTemplate.execute((rstatus) -> {
                try {
                    saveW(frend);
                    saveR(frend);
                    //int a = 1 / 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    wstatus.setRollbackOnly();
                    rstatus.setRollbackOnly();
                    return false;
                }
                return true;
            });
            return true;
        });
    }

    /*@Override
    @MultiTransactional(value = {DbTxConstants.DB1_TX, DbTxConstants.DB2_TX})
    public void saveAll(Frend frend) {
        saveW(frend);
        saveR(frend);
        int a = 1 / 0;
    }*/

    @Transactional(transactionManager = "wTransactionManager")
    public void saveAll(Frend frend) throws Exception {
        FrendService frendService = (FrendService) AopContext.currentProxy();
        frendService.saveAllR(frend);
    }

    @Transactional(transactionManager = "rTransactionManager")
    public void saveAllR(Frend frend) {
        saveW(frend);
        saveR(frend);
        int a = 1 / 0;
    }
}
