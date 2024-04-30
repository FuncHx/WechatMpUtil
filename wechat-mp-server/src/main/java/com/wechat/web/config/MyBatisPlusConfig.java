package com.wechat.web.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.wechat.mp.config.typehandler.ArrayListTypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Set;

@EnableTransactionManagement
@Configuration //配置类
public class MyBatisPlusConfig {
    //添加分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.getTypeHandlerRegistry().register(ArrayList.class, ArrayListTypeHandler.class);
            }
        };
    }

}
