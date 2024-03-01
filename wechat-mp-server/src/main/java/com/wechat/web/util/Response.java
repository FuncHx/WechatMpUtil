package com.wechat.web.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {

    private Integer code;

    private String message;

    private Object data;

    /**
     * 构造器私有
     */
    private Response() {
    }

    /**
     * 返回成功
     */
    public static Response ok() {
        Response r = new Response();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * 返回失败
     */
    public static Response error() {
        Response r = new Response();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /**
     * 设置特定结果
     */
    public static Response setResult(ResponseEnum responseEnum) {
        Response r = new Response();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    public Response message(String message) {
        this.setMessage(message);
        return this;
    }

    public Response code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Response data(Object value) {
        this.data = value;
        return this;
    }

    public Response data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
