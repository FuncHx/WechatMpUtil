package com.wechat.web.controller;

import com.wechat.web.except.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @GetMapping("/error/exthrow")
    public void rethrow(HttpServletRequest request) {
        throw ((BusinessException) request.getAttribute("filter.error"));
    }
}
