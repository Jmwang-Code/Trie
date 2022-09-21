package com.tuling.datasource.dynamic.mybatis.aop;

import com.tuling.datasource.dynamic.mybatis.service.transaction.ComboTransaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MultiTransactionAop {

    private final ComboTransaction comboTransaction;

    @Autowired
    public MultiTransactionAop(ComboTransaction comboTransaction) {
        this.comboTransaction = comboTransaction;
    }

    @Pointcut("within(com.tuling.datasource.dynamic.mybatis.service.impl.*)")
    public void pointCut() {
    }

    @Around("pointCut() && @annotation(multiTransactional)")
    public Object inMultiTransactions(ProceedingJoinPoint pjp, MultiTransactional multiTransactional) {
        return comboTransaction.inCombinedTx(() -> {
            try {
                return pjp.proceed();       //执行目标方法
            } catch (Throwable throwable) {
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException) throwable;
                }
                throw new RuntimeException(throwable);
            }
        }, multiTransactional.value());
    }



}
