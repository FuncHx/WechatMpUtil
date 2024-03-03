package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.mapper.SysMenuMapper;
import com.wechat.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements PermissionService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public List<SysMenu> selectListByUserId(Integer id){
        return sysMenuMapper.selectListByUserId(id);
    }
    public List<Role> selectRoleListByUserId(Integer id){
        return sysMenuMapper.selectRoleListByUserId(id);
    }

}
