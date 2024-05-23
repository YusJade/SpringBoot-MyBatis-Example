package com.yuxeng.display.interceptor;

import com.yuxeng.display.adminmodel.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminLoginInterceptor implements HandlerInterceptor {

  // 在抵达 Controller 之前被调用
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("AdminLoginInterceptor::preHandle is executed.");
    try {
      HttpSession session = request.getSession();
      Admin admin = (Admin) session.getAttribute("admin");
      // 获取 session 里的值， 为空说明用户此前没有登陆
      if (admin != null) {
        return true; // 放行
      }
//      response.sendRedirect(request.getContextPath() + "/login");
//      System.out.println("rediredt to " + request.getContextPath() + "/login");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 设置状态码为 401，拦截请求
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }

  // 在抵达 Controller 之后被调用
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    System.out.println("AdminLoginInterceptor::postHandle is executed.");
  }

  // 在完成响应之后被调用
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    System.out.println("AdminLoginInterceptor::afterCompletion is executed.");
  }
}