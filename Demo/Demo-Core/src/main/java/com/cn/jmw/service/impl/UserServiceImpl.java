package com.cn.jmw.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.jmw.entity.pojo.UserRegisterParam;
import com.cn.jmw.entity.table.User;
import com.cn.jmw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 写注释的暖男jmw
 * @Description TODO
 * @date 2022年09月23日 18:13
 * @Version 1.0
 */
@Service
@Slf4j
public abstract class UserServiceImpl implements UserService {

    private final UserService userService;

    @Value("${datart.user.active.send-mail:false}")
    private boolean sendEmail;

    public UserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean register(UserRegisterParam userRegisterParam) throws MessagingException, UnsupportedEncodingException {
        return register(userRegisterParam, sendEmail);
    }

    @Override
    @Transactional
    public boolean register(UserRegisterParam userRegisterParam, boolean sendMail) throws MessagingException, UnsupportedEncodingException {
        if (!(userService.getOne(new LambdaQueryWrapper<User>().select(
                User::getUsername
        )).getUsername() ==userRegisterParam.getUsername())){
            log.error("The username({}) has been registered", userRegisterParam.getUsername());
        }
        if (!(userService.getOne(new LambdaQueryWrapper<User>().select(
                User::getEmail
        )).getEmail() ==userRegisterParam.getEmail())) {
            log.info("The email({}) has been registered", userRegisterParam.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterParam, user);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setId(UUIDGenerator.generate());
        user.setCreateBy(user.getId());
        user.setCreateTime(new Date());
        user.setActive(!sendMail);
        userService.save(user);
        if (!sendMail) {
            userService.save(user);
            return true;
        }
//        mailService.sendActiveMail(user);
        return true;
    }

    static class UUIDGenerator {

        public static String generate() {
            return UUID.randomUUID().toString().replace("-", "");
        }

    }
}
