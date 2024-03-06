package com.wechat.web.controller;


import com.wechat.web.domain.vo.LoginUser;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.service.LoginService;
import com.wechat.web.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("login")
    public Response login(@RequestBody LoginUser user) {
        Response token = loginService.login(user);
        return token;
    }

    @PostMapping("register")
    public Response register(@RequestBody SysUser sysUser) {
        SysUser user = loginService.register(sysUser);
        return Response.ok().data(user);
    }

    @GetMapping("logout")
    public Response logOut() {
        Boolean aBoolean = loginService.logOut();
        return Response.ok();
    }

    @GetMapping("getRouters")
    public Response getRouters(){
        Response response = loginService.getRouters();
        return response;
    }



}
