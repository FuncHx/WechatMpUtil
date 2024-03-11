package com.wechat.web.controller;


import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.domain.entity.TreeSelect;
import com.wechat.web.service.SysMenuService;
import com.wechat.web.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PreAuthorize("@hx.hasAuth('system:menu:list')")
    @PostMapping("getMenu")
    public Response getMenus(@RequestBody SysMenu sysMenu) {
        List<SysMenu> sysMenus = sysMenuService.selectMenuTree(sysMenu);
        return Response.ok().data(sysMenus);
    }

}
