package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.entity.Role;
import com.wechat.web.domain.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService extends IService<SysMenu> {

    public List<SysMenu> selectListByUserId(Integer id);
    public List<Role> selectRoleListByUserId(Integer id);

}
