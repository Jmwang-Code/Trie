package com.cn.jmw.base;

import com.cn.jmw.uitls.JsonUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zhuwukai
 * @date 2021.11.11
 */
@Getter
@Setter
@SuppressWarnings("ALL")
@Accessors(chain = true)
public class R<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
    public static final String SUCCESS_CODE = "200";
    public static final String FAIL_CODE = "-1";
    public static final String TIMEOUT_CODE = "-2";
    /**
     * 统一参数验证异常
     */
    public static final String VALID_EX_CODE = "-9";
    public static final String OPERATION_EX_CODE = "-10";
    /**
     * 调用是否成功标识，0：成功，-1:系统繁忙，此时请开发者稍候再试 详情见[ExceptionCode]
     */
    @ApiModelProperty(value = "响应编码:0/200-请求处理成功")
    private String code;

    /**
     * 调用结果
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 结果消息，如果调用成功，消息通常为空T
     */
    @ApiModelProperty(value = "提示消息")
    private String msg = "ok";

    @ApiModelProperty(value = "请求路径")
    private String path;
    /**
     * 附加数据
     */
    @ApiModelProperty(value = "附加数据")
    private Map<Object, Object> extra;

    /**
     * 响应时间
     */
    @ApiModelProperty(value = "响应时间戳")
    private long timestamp = System.currentTimeMillis();

    private R() {
        this.timestamp = System.currentTimeMillis();
    }

    public R(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public static <E> R<E> result(String code, E data, String msg) {
        return new R<>(code, data, msg);
    }

    /**
     * 请求成功消息
     *
     * @param data 结果
     * @return RPC调用结果
     */
    public static <E> R<E> success(E data) {
        return new R<>(SUCCESS_CODE, data, "ok");
    }

    public static R<Boolean> success() {
        return new R<>(SUCCESS_CODE, true, "ok");
    }

    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data 结果
     * @param msg  消息
     * @return RPC调用结果
     */
    public static <E> R<E> success(E data, String msg) {
        return new R<>(SUCCESS_CODE, data, msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> R<E> fail(String code, String msg) {
        return new R<>(code, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    public static <E> R<E> fail(String msg) {
        return fail(OPERATION_EX_CODE, msg);
    }

    public static <E> R<E> fail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new R<>(OPERATION_EX_CODE, null, String.format(message, args));
    }

    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @return RPC调用结果
     */
    public static <E> R<E> fail(Throwable throwable) {
        return fail(FAIL_CODE, throwable != null ? throwable.getMessage() : DEF_ERROR_MESSAGE);
    }

    public static <E> R<E> validFail(String msg) {
        return new R<>(VALID_EX_CODE, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    public static <E> R<E> validFail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new R<>(VALID_EX_CODE, null, String.format(message, args));
    }

    public static <E> R<E> timeout() {
        return fail(TIMEOUT_CODE, HYSTRIX_ERROR_MESSAGE);
    }


    public R<T> put(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap<>(10);
        }
        this.extra.put(key, value);
        return this;
    }

    public R<T> putAll(Map<Object, Object> extra) {
        if (this.extra == null) {
            this.extra = new HashMap<>(10);
        }
        this.extra.putAll(extra);
        return this;
    }

    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
    public Boolean getIsSuccess() {
        return SUCCESS_CODE.equals(this.code) || "200".equals(this.code);
    }

    /**
     * 逻辑处理是否失败
     *
     * @return
     */
    public Boolean getIsError() {
        return !getIsSuccess();
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
