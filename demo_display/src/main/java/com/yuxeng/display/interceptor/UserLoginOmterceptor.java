package com.yuxeng.display.interceptor;

import com.yuxeng.display.usermodel.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserLoginOmterceptor implements HandlerInterceptor {

  /*
  在Controller前调用
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("[HANDLE] | PreHandle Working");
    try {
      HttpSession session = request.getSession();
      User user = (User) session.getAttribute("user");
      if (user != null) {
        System.out.println("[HANDLE] | PreHandle Allow");
        return true;
      }
      response.sendRedirect(request.getContextPath() + "login");  // 重新导航
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView) throws  Exception{
    System.out.println("[HANDLE] | PostHandle Working");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
    System.out.println("[HANDLE] | AfterHandle Working");
  }

}
