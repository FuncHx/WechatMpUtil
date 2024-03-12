package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.mapper.RoleMapper;
import com.wechat.web.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRole(Role role) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.like(!StringUtils.isEmpty(role.getRoleName()), "role_name", role.getRoleName());
        roleQueryWrapper.like(!StringUtils.isEmpty(role.getRole()), "role", role.getRole());
        List<Role> roles = roleMapper.selectList(roleQueryWrapper);
        roles.forEach(r -> {
            ArrayList<SysMenu> sysMenus = roleMapper.selectMenuById(r.getId());
            r.getData().put("permission",  sysMenus);
        });
        return roles;
    }
}
