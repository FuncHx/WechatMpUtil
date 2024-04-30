package com.wechat.mp;

import com.wechat.mp.entity.WxUser;
import com.wechat.mp.service.WxUserService;
import com.wechat.mp.util.JsonUtils;
import com.wechat.web.domain.entity.SysMenu;
import com.wechat.web.service.SysMenuService;
import com.wechat.web.service.LoginService;
import com.wechat.web.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class MpApplicationTests {

    @Autowired
    private LoginService sysUserService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private WxUserService wxUserService;

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

    @Test
    void  createWxUser() {
//        WxUser wxUser = new WxUser();
//        wxUser.setNickName("W");
//        wxUser.setSubscribe("1");
//        wxUser.setOpenId("test");
//        wxUser.setSex("1");
//        Long[] s = {100L, 2L};
//        wxUser.setTagidList(s);
//        wxUserService.save(wxUser);
        Set<String> strings = new LinkedHashSet<>();
        strings.add("s");
    }


}
