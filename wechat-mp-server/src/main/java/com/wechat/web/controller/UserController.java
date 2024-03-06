package com.wechat.web.controller;


import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.service.LoginService;
import com.wechat.web.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService userService;


    @GetMapping("/userInfo")
    public Response getUserInfo(){
        SysUser user = userService.getUserInfo().getSysUser();
        return Response.ok().data(user);
    }

}
