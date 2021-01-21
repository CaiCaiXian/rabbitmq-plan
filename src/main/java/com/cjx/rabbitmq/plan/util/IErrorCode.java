package com.cjx.rabbitmq.plan.util;

/**
 *@author wshun
 *@date 2020/09/22  14:50:14
 *@doc 封装API的错误码
 *@version 1.0.0
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
