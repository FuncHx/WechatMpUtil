package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    boolean editPassword(String oldPassword, String newPassword);

}
