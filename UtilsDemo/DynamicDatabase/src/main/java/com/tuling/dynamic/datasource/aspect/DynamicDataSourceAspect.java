package com.tuling.dynamic.datasource.aspect;

import com.tuling.dynamic.datasource.DynamicDataSource;
import com.tuling.dynamic.datasource.annotation.WR;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
@Aspect
public class DynamicDataSourceAspect implements Ordered {

    // 前置
    @Before("within(com.tuling.dynamic.datasource.service.impl.*) && @annotation(wr)")
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
