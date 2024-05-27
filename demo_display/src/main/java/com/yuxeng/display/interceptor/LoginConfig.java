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
    // 拦截所有路径
    registration.addPathPatterns("/admin/**");
    // 放行特定的路径
    registration.excludePathPatterns(
        "/admin/login"
        );
    // TODO: 注册用户登录拦截器
    InterceptorRegistration userRegistration = registry.addInterceptor(new UserLoginOmterceptor());
    userRegistration.addPathPatterns("/user/**");
    userRegistration.excludePathPatterns(
        "/user/login",
        "/user/register",
        "/user/send-code",
        "/user/{mail}/code");
  }
}
