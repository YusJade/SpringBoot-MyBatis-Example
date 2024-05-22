package com.yuxeng.display.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 注册管理员登录拦截器
    InterceptorRegistration registration = registry.addInterceptor(new AdminLoginInterceptor());
    registration.addPathPatterns("/**");
    registration.excludePathPatterns(
        "/admin/login"
    );
    // TODO: 注册用户登录拦截器
  }
}
