package com.wechat.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.web.domain.entity.*;
import com.wechat.web.domain.vo.LoginUser;
import com.wechat.web.domain.vo.RouterVo;
import com.wechat.web.except.BusinessException;
import com.wechat.web.mapper.SysMenuMapper;
import com.wechat.web.mapper.SysUserMapper;
import com.wechat.web.service.SysMenuService;
import com.wechat.web.service.LoginService;
import com.wechat.web.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    @Override
    public Response login(LoginUser sysUser) {
        Object o = redisUtil.get(sysUser.getUuid());
        System.out.println(redisUtil.get(sysUser.getUuid()));
        // 验证码逻辑判断
        if (Objects.isNull(o)) {
            throw new BusinessException(ResponseEnum.CODE_ERROR_TIME_OUT);
        }else if (!sysUser.getCode().equals("test")) {
            throw new BusinessException(ResponseEnum.CODE_ERROR);
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        Assert.notNull(authenticate, ResponseEnum.LOGIN_ERROR);
        // 认证通过了，使用用户信息生成token
        CustomUser loginUser = (CustomUser) authenticate.getPrincipal();
        String username = loginUser.getSysUser().getUsername();
        String token = JwtUtil.createJWT(username);
        // 将用户信息保存到redis当中
        redisUtil.set("login"+ loginUser.getUsername(), loginUser);
        redisUtil.expire("login"+ loginUser.getUsername(), 60 * 60 * 24);
        HashMap result = new HashMap();
        result.put("token", token);
        Response data = Response.ok().data(result);
        return data;
    }




    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户注册
     * @param sysUser
     * @return
     */
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

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public CustomUser getUserInfo() {
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> roles = sysMenuMapper.selectRoleListByUserId(customUser.getSysUser().getId());
        customUser.getSysUser().getData().put("roles", roles.stream().map(Role::getRoleName));
        List<SysMenu> routerVos = sysMenuService.selectMenuTreeByUserId(customUser.getSysUser().getId());
        customUser.getSysUser().getData().put("menus", routerVos);
        return customUser;
    }

    @Override
    public Boolean logOut() {
        CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            redisUtil.del("login" + customUser.getUsername());
            return true;
        }catch (Exception E){
            throw new BusinessException(ResponseEnum.LOGOUT_ERROR);
        }
    }

    @Override
    public Response getRouters() {
        CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<RouterVo> routerVos = sysMenuService.buildMenus(sysMenuService.selectMenuTreeByUserId(principal.getSysUser().getId()));
        return Response.ok().data(routerVos);
    }

}
