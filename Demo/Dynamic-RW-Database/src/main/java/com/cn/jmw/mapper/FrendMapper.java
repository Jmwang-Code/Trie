package com.cn.jmw.mapper;


import com.cn.jmw.entity.Frend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FrendMapper   {
    @Select("SELECT * FROM Frend")
    List<Frend> list();

    @Insert("INSERT INTO  Frend(`id`,`name`) VALUES (#{id},#{name})")
    void save(Frend frend);
}