package com.tuling.dynamic.datasource.service;

import com.tuling.dynamic.datasource.annotation.WR;
import com.tuling.dynamic.datasource.entity.Frend;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public interface FrendService  {
    List<Frend> list();

    void save(Frend frend);

}
