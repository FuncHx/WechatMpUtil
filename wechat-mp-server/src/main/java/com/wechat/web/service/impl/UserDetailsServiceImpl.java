package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.mapper.SysUserMapper;
import com.wechat.web.service.PermissionService;
import com.wechat.web.util.Assert;
import com.wechat.web.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据用户名得到用户信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("user_name", username);
        SysUser sysUser= sysUserMapper.selectOne(sysUserQueryWrapper);
        if (sysUser==null) {throw new UsernameNotFoundException("该用户名不存在");}
        //声明一个用于存放用户权限的列表
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获取该用户所拥有的权限
        List<SysMenu> authority = permissionService.selectListByUserId(sysUser.getId());
        //获取该用户所属角色
        List<Role> role = permissionService.selectRoleListByUserId(sysUser.getId());
        //把用户所拥有的权限添加到列表中
        authority.forEach(permission -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPerms()));
        });
        //把用户角色加到列表中
        role.forEach(role1 -> {
            //注意：添加角色的时候要在前面加ROLE_前缀
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role1.getRoleName()));
        });

        if(sysUser.getStatus().equals("0")){
            Assert.isNull(null, ResponseEnum.USER_IS_DISABLE);
        }
        return new CustomUser(sysUser, grantedAuthorities);
    }
}
