package com.wechat.web.controller;


import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.domain.entity.TreeSelect;
import com.wechat.web.service.SysMenuService;
import com.wechat.web.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询菜单
     * @param sysMenu
     * @return
     */
    @PreAuthorize("@hx.hasAuth('system:menu:list')")
    @PostMapping("list")
    public Response getMenus(@RequestBody SysMenu sysMenu) {
        List<SysMenu> sysMenus = sysMenuService.selectMenuTree(sysMenu);
        return Response.ok().data(sysMenus);
    }

    /**
     * 新增菜单
     * @param sysMenu
     * @return
     */
    @PreAuthorize("@hx.hasAuth('system:menu:add')")
    @PostMapping("add")
    public Response addMenus(@RequestBody SysMenu sysMenu) {
        boolean save = sysMenuService.save(sysMenu);
        return Response.ok().message("新增成功！");
    }

    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    @PreAuthorize("@hx.hasAuth('system:menu:update')")
    @PostMapping("update")
    public Response updateMenus(@RequestBody SysMenu sysMenu) {
        boolean save = sysMenuService.updateById(sysMenu);
        return Response.ok().message("修改成功！");
    }


    @PreAuthorize("@hx.hasAuth('system:menu:delete')")
    @GetMapping("delete/{id}")
    public Response deleteMenu(@PathVariable Integer id) {
        boolean b = sysMenuService.removeById(id);
        return Response.ok().message("删除成功！");
    }


}
