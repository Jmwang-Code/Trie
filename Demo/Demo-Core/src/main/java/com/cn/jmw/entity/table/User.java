package com.cn.jmw.entity.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月23日 17:34
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class User extends BaseEntity {

    private String email;

    private String username;

    private String password;

    private Boolean active;

    private String name;

    private String description;

    private String avatar;

    public static void main(String[] args) {
        //允许以builder的方式给属性赋值。
        BaseEntity build = User.builder().permission(1).build();
        System.out.println(build);
    }
}
