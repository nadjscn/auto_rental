package com.xzit.rental.utils;

/**
 * 定义HTTP状态码常量
 * 这些常量用于表示不同类型的HTTP响应状态。
 */
public class ResultCode {
    // 表示请求成功的状态码
    public static final Integer SUCCESS = 200;
    // 表示服务器内部错误的状态码
    public static final Integer ERROR = 500;
    // 表示请求需要用户验证的状态码
    public static final Integer UNAUTHENTICATED = 401;
    // 表示请求未授权的状态码
    public static final Integer UNAUTHORIZED = 403;
}
