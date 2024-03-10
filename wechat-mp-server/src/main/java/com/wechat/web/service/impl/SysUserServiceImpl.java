package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.except.BusinessException;
import com.wechat.web.mapper.SysUserMapper;
import com.wechat.web.service.SysUserService;
import com.wechat.web.util.ResponseEnum;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    /**
     * 修改密码
     * @param oldPassword 用户输入的旧密码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public boolean editPassword(String oldPassword, String newPassword) {
        CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserMapper.selectById(user.getSysUser().getId());
        // 将用户输入的旧密码与数据库中的密码进行校验
        boolean matches = bCryptPasswordEncoder.matches(oldPassword, sysUser.getPassword());
        if (!matches) {
            throw new BusinessException(ResponseEnum.LOGIN_PASSWORD_ERROR);
        }
        // 更新数据库中的密码
        SysUser newUser = new SysUser();
        newUser.setId(sysUser.getId());
        newUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        int i = sysUserMapper.updateById(newUser);
        return i > 0;
    }
}
