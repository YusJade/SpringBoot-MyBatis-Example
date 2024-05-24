package com.yuxeng.display.adminmodel;

import com.yuxeng.display.bookmodel.Book;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
   * @param username 用户名
   * @param password 密码
   * @return -1: 用户不存在 | 0:密码错误 | 1:登录成功
   */
  @PostMapping("/login")
  public Integer login(HttpServletRequest request, HttpServletResponse response,
      @RequestParam String username,
      @RequestParam String password) {
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

  @GetMapping("/{id}")
  public Admin getInfo(@PathVariable Integer id) {
    // 从 Session 中获取
    Admin admin = adminService.getAdminById(id);
    if (admin == null) {
      System.out.println("管理员未登录");
      return  new Admin();
    }
    System.out.println("已登录的管理员：" + admin);
    return admin;
  }

  @PutMapping("/{id}")
  public int updateInfo(@PathVariable int id,
      @RequestBody Admin adminUpdated) {
    adminUpdated.setId(id);
    adminService.updateBasicInfo(adminUpdated);
    return  adminUpdated.getId();
  }

  @GetMapping("/book-search")
  public List<Book> getBookByKeyword(@RequestParam String keyword) {
    return adminService.getAdminByKeyword(keyword);
  }

  @PostMapping("/book-insert")
  public int insertBook(@RequestBody Book book) {
    return adminService.insertBook(book);
  }

  @PostMapping("/book-delete")
  public int deleteBookById(@RequestParam int id) {
    return  adminService.deleteBookById(id);
  }

  @PostMapping("/book-update")
  public int updateBook(@RequestBody Book book) {

    return -1;
  }

  @PutMapping("/password/{id}")
  public boolean updatePassword(@PathVariable int id,
      @RequestBody Map<String, String> passwords) {
    // 获取当前管理员信息
    String originPassword = passwords.get("origin_password");
    String newPassword = passwords.get("new_password");
    Admin admin = adminService.getAdminById(id);
    if (admin != null && originPassword != null && newPassword != null) {
      return adminService.updatePassword(admin.getId(), originPassword, newPassword);
    }
    return false;
  }

}
