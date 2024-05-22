package com.yuxeng.display.adminmodel;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

  @Resource
  AdminDao adminDao;

  @PostMapping("/login")
  public Admin login(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Admin loginAdmin,
      @CookieValue("leave-an-easter-egg") String cookieVal) {

    List<Admin> queryList = adminDao.findAdmin(loginAdmin);
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      System.out.println(cookie.getValue());
    }
    System.out.println("@CookieValue: " + cookieVal);
    response.addCookie(new Cookie("leave-an-easter-egg", "[Strategic-Assault-Mech]:SAM"));
    if (!queryList.isEmpty()) {
      return queryList.get(0);
    }
    return new Admin();
  }
}
