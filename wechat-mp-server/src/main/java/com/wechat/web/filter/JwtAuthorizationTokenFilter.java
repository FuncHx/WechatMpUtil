package com.wechat.web.filter;

import com.wechat.web.domain.entity.CustomUser;
import com.wechat.web.except.BusinessException;
import com.wechat.web.service.impl.UserDetailsServiceImpl;
import com.wechat.web.util.JwtUtil;
import com.wechat.web.util.RedisUtil;
import com.wechat.web.util.ResponseEnum;
import com.wechat.web.util.SpringContextUtils;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        UserDetailsServiceImpl userDetailsService = (UserDetailsServiceImpl)SpringContextUtils.getBean(UserDetailsServiceImpl.class);
        redisUtil = (RedisUtil) SpringContextUtils.getBean(RedisUtil.class);
        String token = request.getHeader("authorization");
        if (StringUtils.hasText(token)){
            Claims claim = null;
            try {
                claim = jwtUtil.parseJWT(token);
            } catch (Exception e) {
                request.setAttribute("filter.error", new BusinessException(ResponseEnum.TOKEN_ERROR));
                //将异常分发到/error/exthrow控制器
                request.getRequestDispatcher("/error/exthrow").forward(request, response);
                return;
            }
            if (claim == null) {
                // 异常捕获，发送到error controller
                request.setAttribute("filter.error", new BusinessException(ResponseEnum.TOKEN_ERROR));
                //将异常分发到/error/exthrow控制器
                request.getRequestDispatcher("/error/exthrow").forward(request, response);
                filterChain.doFilter(request, response);
                return;
            }
            String redisKey = "login" + claim.getSubject();
            CustomUser user = (CustomUser)redisUtil.get(redisKey);
            if (Objects.isNull(user)){
                request.setAttribute("filter.error", new BusinessException(ResponseEnum.LOGIN_AUTH_ERROR));
                request.getRequestDispatcher("/error/exthrow").forward(request, response);
            }else {
                UserDetails userDetails = userDetailsService.loadUserByUsername(claim.getSubject());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
