package com.wechat.web.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.domain.entity.SysUser;
import com.wechat.web.except.BusinessException;
import com.wechat.web.mapper.SysUserMapper;
import com.wechat.web.service.SysUserService;
import com.wechat.web.service.impl.UserDetailsServiceImpl;
import com.wechat.web.util.JwtUtil;
import com.wechat.web.util.ResponseEnum;
import com.wechat.web.util.SpringContextUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        UserDetailsServiceImpl userDetailsService = (UserDetailsServiceImpl)SpringContextUtils.getBean(UserDetailsServiceImpl.class);
        String token = request.getHeader("authorization");
        if (null != token){
            Claims claim = null;
            try {
                claim = jwtUtil.parseJWT(token);
            } catch (Exception e) {
                request.setAttribute("filter.error", new BusinessException(ResponseEnum.TOKEN_ERROR));
                //将异常分发到/error/exthrow控制器
                request.getRequestDispatcher("/error/exthrow").forward(request, response);
            }
            if (claim == null) {
                // 异常捕获，发送到error controller
                request.setAttribute("filter.error", new BusinessException(ResponseEnum.TOKEN_ERROR));
                //将异常分发到/error/exthrow控制器
                request.getRequestDispatcher("/error/exthrow").forward(request, response);
            }
            if (null != claim.getSubject() & SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(claim.getSubject());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
