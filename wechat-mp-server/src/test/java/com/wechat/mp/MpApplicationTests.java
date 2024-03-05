package com.wechat.mp;

import com.wechat.mp.util.JsonUtils;
import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.service.SysMenuService;
import com.wechat.web.service.SysUserService;
import com.wechat.web.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class MpApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));
    }


    @Test
    void createSysUser() {
        UserDetails root = userDetailsService.loadUserByUsername("root");
        Collection<? extends GrantedAuthority> authorities = root.getAuthorities();
        authorities.forEach(authoritie -> {
            System.out.println(authoritie);
        });
    }

    @Test
    void buildMenusTest() {
        List<SysMenu> menuList = sysMenuService.list();
        System.out.println(JsonUtils.toJson(sysMenuService.buildMenus(menuList)));
    }

    @Test
    void buildMenusTreeTest() {
        List<SysMenu> menuList = sysMenuService.list();
        System.out.println(JsonUtils.toJson(sysMenuService.buildMenuTreeSelect(menuList)));
    }


}
