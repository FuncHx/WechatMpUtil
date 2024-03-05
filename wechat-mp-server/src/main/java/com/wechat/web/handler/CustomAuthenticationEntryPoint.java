package com.wechat.web.handler;


import com.alibaba.fastjson2.JSON;
import com.wechat.mp.util.JsonUtils;
import com.wechat.web.except.BusinessException;
import com.wechat.web.util.Response;
import com.wechat.web.util.ResponseEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 自定义登录失效处理器
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.toJson(Response.error().data(ResponseEnum.LOGIN_AUTH_ERROR)));
    }
}
