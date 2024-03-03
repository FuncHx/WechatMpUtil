package com.wechat.web.controller;


import com.wechat.web.domain.LoginUser;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.except.BusinessException;
import com.wechat.web.service.SysUserService;
import com.wechat.web.util.Response;
import com.wechat.web.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private SysUserService userService;


    @PostMapping("login")
    public Response login(@RequestBody LoginUser user) {
        String token = userService.login(user);
        HashMap result = new HashMap();
        result.put("token", token);
        return Response.ok().data(result);
    }

    @PostMapping("register")
    public Response register(@RequestBody SysUser sysUser) {
        SysUser user = userService.register(sysUser);
        return Response.ok().data(user);
    }


}
