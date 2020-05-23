package com.orc.demo.util.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * AutoInterceptor添加到配置类中，使拦截器生效
 * @author orckid
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private AutoInterceptor autoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoInterceptor);
    }
}
