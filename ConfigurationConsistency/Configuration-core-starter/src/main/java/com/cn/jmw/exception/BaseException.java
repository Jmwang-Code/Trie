package com.cn.jmw.exception;

/**
 * 异常接口类
 *
 * @author zhuwukai
 * @date 2021.11.11
 * @version 1.0,
 */
public interface BaseException {

    /**
     * 统一参数验证异常码
     */
    int BASE_VALID_PARAM = -9;

    /**
     * 返回异常信息
     *
     * @return
     */
    String getMessage();

    /**
     * 返回异常编码
     *
     * @return
     */
    int getCode();

}
