package com.wechat.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.web.domain.vo.LoginUser;
import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.util.Response;

public interface LoginService  {

    Response login(LoginUser sysUser);

    SysUser register(SysUser sysUser);

    CustomUser getUserInfo();

    Boolean logOut();

    Response getRouters();
}
