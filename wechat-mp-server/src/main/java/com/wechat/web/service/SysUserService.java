package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.SysUser;
import com.wechat.web.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

public interface SysUserService extends IService<SysUser> {

    String login(SysUser sysUser);

    SysUser register(SysUser sysUser);

}
