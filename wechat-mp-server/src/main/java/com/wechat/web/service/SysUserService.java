package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.LoginUser;
import com.wechat.web.domain.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    String login(LoginUser sysUser);

    SysUser register(SysUser sysUser);

}
