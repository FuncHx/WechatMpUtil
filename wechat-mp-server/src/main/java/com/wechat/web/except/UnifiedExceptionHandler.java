package com.wechat.web.except;

import com.wechat.web.util.Response;
import com.wechat.web.util.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;



@Slf4j
@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = java.nio.file.AccessDeniedException.class)
    public Response handleAccessDeniedException(java.nio.file.AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return Response.error().code(-1).message(e.getMessage());
    }

    /**
     * 处理用户没有权限的控制器
     * @param e
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public Response accessDeniedException(AccessDeniedException e) {
        return Response.setResult(ResponseEnum.AUTH_ERROR);
    }

    /**
     * 处理用户登录失效的处理器
     * @param e
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public Response authenticationException(AuthenticationException e) {
        return Response.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
    }

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BusinessException.class,
    })
    public Response handleBusinessException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.error().code(e instanceof BusinessException ? ((BusinessException) e).getCode(): 201).message(e.getMessage());
    }

    /**
     * Controller上一层相关异常
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public Response handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        return Response.error().message("servlet请求异常").code(-102);
    }

    /**
     * 未定义异常
     * 当controller中抛出Exception，则捕获
     */
    @ExceptionHandler(value = Exception.class)
    public Response handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.error();
    }
}
