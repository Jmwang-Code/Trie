package com.cn.jmw.aspect;

import com.cn.jmw.DynamicDataSource;
import com.cn.jmw.annotation.WR;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect //动态切面
public class DynamicDataSourceAspect implements Ordered {

    // 前置
    @Before("within(com.cn.jmw.service.impl.*) && @annotation(wr)")
    public void before(JoinPoint point, WR wr){
        String name = wr.value();
        DynamicDataSource.name.set(name);

        System.out.println(name);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    // 环绕通知
}
