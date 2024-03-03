package com.wechat.web.domain.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Data
public class CustomUser implements UserDetails {

    /**
     * 用户实体对象，要调取用户信息时直接获取这个实体对象。
     */
    private SysUser sysUser;

    private Collection<? extends GrantedAuthority> authority;

    public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> collection){
        this.authority = collection;
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authority;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
