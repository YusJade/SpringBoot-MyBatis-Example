package com.yuxeng.display.interceptor;

import com.yuxeng.display.adminmodel.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminLoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("AdminLoginInterceptor::preHandle is executed.");
    try {
      HttpSession session = request.getSession();
      Admin admin = (Admin) session.getAttribute("admin");
      if (admin != null) {
        return true;
      }
//      response.sendRedirect(request.getContextPath() + "/login");
//      System.out.println("rediredt to " + request.getContextPath() + "/login");
    } catch (Exception e) {
      e.printStackTrace();
    }
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    System.out.println("AdminLoginInterceptor::postHandle is executed.");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    System.out.println("AdminLoginInterceptor::afterCompletion is executed.");
  }
}