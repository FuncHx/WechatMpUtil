package com.wechat.web.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {

    ERROR(-1, "服务器内部错误"),
    SUCCESS(200, "成功"),
    LOGIN_ERROR(201, "用户名或密码错误"),
    USER_IS_DISABLE(202, "用户账号被停用"),
    USER_EXITS(203, "用户已经存在"),
    PASSWORD_NULL_ERROR(204, "密码不能为空"),
    CODE_ERROR(205, "验证码错误"),
    TOKEN_ERROR(206, "token 异常，请重新登录！"),
    AUTH_ERROR(207, "用户权限不足"),
    CODE_ERROR_TIME_OUT(208, "验证码已过期！"),
    LOGIN_PASSWORD_ERROR(209, "密码错误"),
    LOGIN_LOKED_ERROR(210, "用户被锁定"),
    LOGIN_AUTH_ERROR(211, "未登录");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

}
