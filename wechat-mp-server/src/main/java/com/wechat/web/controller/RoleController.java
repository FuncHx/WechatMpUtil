package com.wechat.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.domain.entity.TreeSelect;
import com.wechat.web.service.RoleService;
import com.wechat.web.service.SysMenuService;
import com.wechat.web.util.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private SysMenuService sysMenuService;

    @PreAuthorize("@hx.hasAuth('system:role:list')")
    @PostMapping("list")
    public Response list(@RequestBody Role role) {
        List<Role> list = roleService.listRole(role);
        List<TreeSelect> menuList = sysMenuService.buildMenuTreeSelect(sysMenuService.list());
        HashMap result = new HashMap();
        result.put("roles", list);
        result.put("treeSelect", menuList);
        return Response.ok().data(result);
    }

    @PreAuthorize("@hx.hasAuth('system:role:update')")
    @PostMapping("update")
    public Response update(@RequestBody Role role) {
        boolean b = roleService.updateById(role);
        return Response.ok().message("修改成功！");
    }

    @PreAuthorize("@hx.hasAuth('system:role:add')")
    @PostMapping("add")
    public Response add(@RequestBody Role role) {
        boolean save = roleService.save(role);
        return Response.ok().message("添加成功！");
    }

    @PreAuthorize("@hx.hasAuth('system:role:delete')")
    @GetMapping("delete/{id}")
    public Response delete(@PathVariable Integer id) {
        boolean b = roleService.removeById(id);
        return Response.ok().message("删除成功！");
    }

}
