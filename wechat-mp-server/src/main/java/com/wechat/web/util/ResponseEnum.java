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
