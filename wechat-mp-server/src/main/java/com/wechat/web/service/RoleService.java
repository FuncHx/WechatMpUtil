package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> listRole(Role role);
}
