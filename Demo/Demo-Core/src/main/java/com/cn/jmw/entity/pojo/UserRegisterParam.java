package com.cn.jmw.entity.pojo;

import com.cn.jmw.consts.Const;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月23日 18:18
 * @Version 1.0
 */
@NotNull
@Data
public class UserRegisterParam {

    @NotBlank(message = "Username can not be empty")
    private String username;

    @NotBlank(message = "Password can not be empty")
    @Pattern(regexp = Const.REG_USER_PASSWORD, message = "Password length should be 6-20 characters")
    private String password;

    private String name;

    @NotBlank(message = "email can not be empty")
    @Pattern(regexp = Const.REG_EMAIL, message = "Invalid email format")
    private String email;

}
