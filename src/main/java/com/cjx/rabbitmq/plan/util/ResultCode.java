package com.cjx.rabbitmq.plan.util;

/**
 *@author wshun
 *@date 2020/09/15  13:57:49
 *@doc 枚举一些常用API操作码
 *@version 1.0.0
 */
public enum ResultCode implements IErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),
    /**
     * 参数检验失败
     */
    VALIDATE_FAILED(404, "参数检验失败"),
    /**
     * 暂未登录或token已经过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 没有相关权限
     */
    FORBIDDEN(403, "没有相关权限"),
    /**
     * 系统未知异常
     */
    SERVER_ERROR(999,"抱歉，系统繁忙，请稍后重试！");

    private long code;

    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
