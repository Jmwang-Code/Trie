package com.tuling.datasource.dynamic.mybatis.service.transaction;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Component
public class ComboTransaction {

    @Autowired
    private Db1TxBroker db1TxBroker;

    @Autowired
    private Db2TxBroker db2TxBroker;

    public <V> V inCombinedTx(Callable<V> callable, String[] transactions) {
        if (callable == null) {
            return null;
        }
        // 相当于循环 [wTransactionManager,wTransactionManager]
        Callable<V> combined = Stream.of(transactions)
                .filter(ele -> !StringUtils.isEmpty(ele))
                .distinct()
                .reduce(callable, (r, tx) -> {
                    switch (tx) {
                        case DbTxConstants.DB1_TX:
                            return () -> db1TxBroker.inTransaction(r);
                        case DbTxConstants.DB2_TX:
                            return () -> db2TxBroker.inTransaction(r);
                        default:
                            return null;
                    }
                }, (r1, r2) -> r2);

        try {
            return combined.call();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
