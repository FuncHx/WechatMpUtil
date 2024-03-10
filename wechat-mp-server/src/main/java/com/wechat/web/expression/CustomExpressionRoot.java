package com.wechat.web.expression;


import com.wechat.web.domain.entity.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("xh")
public class CustomExpressionRoot {

    public boolean hasAuth(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser principal = (CustomUser) authentication.getPrincipal();
        return principal.getSysUser().getPermission().contains(authority);
    }

}
