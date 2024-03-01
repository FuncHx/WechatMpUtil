package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.Permission;
import com.wechat.web.domain.Role;
import com.wechat.web.mapper.PermissionMapper;
import com.wechat.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> selectListByUserId(Integer id){
        return permissionMapper.selectListByUserId(id);
    }
    public List<Role> selectRoleListByUserId(Integer id){
        return permissionMapper.selectRoleListByUserId(id);
    }

}
