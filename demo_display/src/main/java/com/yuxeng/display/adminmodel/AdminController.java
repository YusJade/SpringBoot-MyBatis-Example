package com.yuxeng.display.adminmodel;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

  @Resource
  AdminService adminService;

  /**
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param loginInfo 用户名与密码
   * @return -1: 用户不存在 | 0:密码错误 | 1:登录成功
   */
  @PostMapping("/login")
  public Integer login(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Map<String, String> loginInfo) {
    String username = loginInfo.get("username");
    String password = loginInfo.get("password");
    Admin admin = adminService.getAdminByUsername(username);
    if (admin != null) {
      if (admin.getPassword().equals(password)) {
        // 将管理员信息写入 session 中
        request.getSession().setAttribute("admin", admin);
        return 1; // 登录成功
      } else {
        return 0; // 密码错误
      }
    }
    return -1; // 用户不存在
  }

  @GetMapping("/info")
  public Admin getInfo(HttpServletRequest request) {
    Admin admin = (Admin) request.getSession().getAttribute("admin");
    if (admin == null) {
      System.out.println("管理员未登录");
      return  new Admin();
    }
    System.out.println("已登录的管理员：" + admin);
    return admin;
  }
}
