package com.wei.config;

import com.wei.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //配置类
public class WebConfig implements WebMvcConfigurer {
    //注入拦截器
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**"); //要拦截的资源路径
    }
}
