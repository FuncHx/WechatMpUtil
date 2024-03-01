package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.Permission;
import com.wechat.web.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService extends IService<Permission> {

    public List<Permission> selectListByUserId(Integer id);
    public List<Role> selectRoleListByUserId(Integer id);

}
