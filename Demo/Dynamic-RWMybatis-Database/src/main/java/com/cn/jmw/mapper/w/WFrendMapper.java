package com.cn.jmw.mapper.w;


import com.cn.jmw.entity.Frend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WFrendMapper {
    @Select("SELECT * FROM Frend")
    List<Frend> list();

    @Insert("INSERT INTO  frend(`name`) VALUES (#{name})")
    void save(Frend frend);
}