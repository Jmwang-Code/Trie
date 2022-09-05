package com.cn.jmw.exception.code;

/**
 * @author zhuwukai
 * @date 2021.11.11
 */
public interface BaseExceptionCode {
    /**
     * 异常编码
     *
     * @return
     */
    int getCode();

    /**
     * 异常消息
     *
     * @return
     */
    String getMsg();
}
