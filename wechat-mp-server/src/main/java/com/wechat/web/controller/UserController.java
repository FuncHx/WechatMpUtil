package com.wechat.web.controller;


import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.except.BusinessException;
import com.wechat.web.service.LoginService;
import com.wechat.web.service.SysUserService;
import com.wechat.web.util.FileUtils;
import com.wechat.web.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SysUserService userService;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @GetMapping("userInfo")
    public Response getUserInfo(){
        SysUser user = loginService.getUserInfo().getSysUser();
        return Response.ok().data(user);
    }

    @PostMapping("update")
    public Response updateUser(@RequestBody SysUser user) {
        boolean save = userService.updateById(user);
        if (!save) {
           throw new BusinessException("修改失败，请联系管理员！", 201);
        }
        return Response.ok();
    }

    @PostMapping("editPassword")
    public Response editPassword(@RequestBody HashMap<String, String> params) {
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");
        System.out.println(oldPassword);
        System.out.println(newPassword);
        userService.editPassword(oldPassword, newPassword);
        return Response.ok();
    }

    @PostMapping("editAvatar")
    public Response editAvatar(MultipartFile file, HttpServletRequest req) throws IOException {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String fileName = FileUtils.saveFile(file, uploadFolder);
        String imageUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()  + staticAccessPath.replace("**", "") + fileName;
        user.getSysUser().setAvatar(imageUrl);
        userService.updateById(user.getSysUser());
        return Response.ok().data(imageUrl).message("头像修改成功");
    }

}
