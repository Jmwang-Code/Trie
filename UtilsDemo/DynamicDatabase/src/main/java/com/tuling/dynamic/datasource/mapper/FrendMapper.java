package com.tuling.dynamic.datasource.mapper;


import com.tuling.dynamic.datasource.entity.Frend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: wangyi
 * @Date: 2020/12/12 01:16
 * @Description: 
 */
public interface FrendMapper   {
    @Select("SELECT * FROM Frend")
    List<Frend> list();

    @Insert("INSERT INTO  frend(`name`) VALUES (#{name})")
    void save(Frend frend);
}