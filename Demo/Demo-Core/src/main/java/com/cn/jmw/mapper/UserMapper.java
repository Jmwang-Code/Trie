package com.cn.jmw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.jmw.entity.table.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月23日 18:01
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
