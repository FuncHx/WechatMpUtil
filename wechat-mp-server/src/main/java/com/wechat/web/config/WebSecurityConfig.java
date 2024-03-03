package com.wechat.web.config;


import com.wechat.web.filter.JwtAuthorizationTokenFilter;
import com.wechat.web.handler.*;
import com.wechat.web.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    /*
    权限不足处理器
     */
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /*
    登录失效处理器
     */
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /*
    登出成功处理器
     */
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    /**
     * Jwt过滤器
     */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
        http.authorizeRequests()
                        .antMatchers("/user/login").anonymous()
                        .antMatchers("/user/register").anonymous()
                        .antMatchers("/captchaImage").anonymous()
                        .anyRequest().authenticated();

        http.addFilterBefore(new JwtAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 用户登出成功时的处理
        http.logout().logoutSuccessHandler(customLogoutSuccessHandler);
        // 无权访问处理
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        // 用户登录失效的处理
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * 配置哪些请求不拦截
     * 排除swagger相关请求
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().antMatchers(
                    "/favicon.ico",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/v2/**",
                    "/swagger-ui.html/**",
                    "/doc.html");
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
