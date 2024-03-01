package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.CustomUser;
import com.wechat.web.domain.SysUser;
import com.wechat.web.mapper.SysUserMapper;
import com.wechat.web.service.SysUserService;
import com.wechat.web.util.Assert;
import com.wechat.web.util.JwtUtil;
import com.wechat.web.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public String login(SysUser sysUser) {
        System.out.println(sysUser);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(sysUser.getUserName(), sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        Assert.notNull(authenticate, ResponseEnum.LOGIN_ERROR);
        // 认证通过了，使用用户信息生成token
        CustomUser loginUser = (CustomUser) authenticate.getPrincipal();
        System.out.println(loginUser);
        Integer userId = loginUser.getSysUser().getId();
        String token = JwtUtil.createJWT(userId.toString());
        return token;
    }

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public SysUser register(SysUser sysUser) {
        // 验证用户是否已经存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("user_name", sysUser.getUserName());
        Assert.isNull(sysUserMapper.selectOne(query), ResponseEnum.USER_EXITS);
        // 将用户保存到数据库
        SysUser user = new SysUser();
        user.setUserName(sysUser.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        user.setEmail(sysUser.getEmail());
        user.setNickName(sysUser.getNickName());
        user.setPhonenumber(sysUser.getPhonenumber());
        user.setSex(sysUser.getSex());
        user.setAvatar(sysUser.getAvatar());
        user.setStatus("1");
        int insert = sysUserMapper.insert(user);
        return user;
    }

}
