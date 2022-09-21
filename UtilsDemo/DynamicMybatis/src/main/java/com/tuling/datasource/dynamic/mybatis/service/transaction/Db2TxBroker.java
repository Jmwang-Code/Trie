package com.tuling.datasource.dynamic.mybatis.service.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Callable;

@Component
public class Db2TxBroker {

    @Transactional(DbTxConstants.DB2_TX)
    public <V> V inTransaction(Callable<V> callable) {
        try {
            return callable.call();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
