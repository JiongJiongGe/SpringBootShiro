package com.shiro.config.web;

import com.shiro.interceptor.WebInInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/6/5
 * @Time: 上午9:44
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new WebInInterceptor()).addPathPatterns("/web/in/**");
        super.addInterceptors(registry);
    }
}
