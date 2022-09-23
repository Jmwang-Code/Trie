package com.cn.jmw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.jmw.entity.pojo.UserRegisterParam;
import com.cn.jmw.entity.table.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月23日 18:07
 * @Version 1.0
 */
public interface UserService extends IService<User> {

    public boolean register(UserRegisterParam userRegisterParam) throws MessagingException, UnsupportedEncodingException;

    public boolean register(UserRegisterParam userRegisterParam,boolean sendMail) throws MessagingException, UnsupportedEncodingException;

}
