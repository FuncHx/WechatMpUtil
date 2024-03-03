package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.LoginUser;
import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.except.BusinessException;
import com.wechat.web.mapper.SysUserMapper;
import com.wechat.web.service.SysUserService;
import com.wechat.web.util.Assert;
import com.wechat.web.util.JwtUtil;
import com.wechat.web.util.RedisUtil;
import com.wechat.web.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(LoginUser sysUser) {
        Object o = redisUtil.get(sysUser.getUuid());
        System.out.println(redisUtil.get(sysUser.getUuid()));
        // 验证码逻辑判断
        if (Objects.isNull(o)) {
            throw new BusinessException(ResponseEnum.CODE_ERROR_TIME_OUT);
        }else if (!o.toString().equals(sysUser.getCode())) {
            throw new BusinessException(ResponseEnum.CODE_ERROR);
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        Assert.notNull(authenticate, ResponseEnum.LOGIN_ERROR);
        // 认证通过了，使用用户信息生成token
        CustomUser loginUser = (CustomUser) authenticate.getPrincipal();
        String username = loginUser.getSysUser().getUsername();
        String token = JwtUtil.createJWT(username);
        return token;
    }

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public SysUser register(SysUser sysUser) {
        // 验证用户是否已经存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("user_name", sysUser.getUsername());
        Assert.isNull(sysUserMapper.selectOne(query), ResponseEnum.USER_EXITS);
        // 将用户保存到数据库
        SysUser user = new SysUser();
        user.setUsername(sysUser.getUsername());
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
