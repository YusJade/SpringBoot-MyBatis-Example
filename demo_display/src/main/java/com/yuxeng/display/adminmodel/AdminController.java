package com.yuxeng.display.adminmodel;

import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

  @Resource
  AdminService adminService;

  /**
   * 管理员登录接口
   * @param request  HttpServletRequest
   * @param loginInfo 用户名和密码
   * @return -1： 用户不存在； 0：密码错误；1：登录成功
   */
  @PostMapping("/login")
  public Responses<Integer> login(HttpServletRequest request,
      @RequestBody Map<String, String> loginInfo) {
    int res = adminService.login(loginInfo);
    switch (res) {
      case -1:
        return new  Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "管理员不存在", 0);
      case 0:
        return new  Responses<>(ResponseCode.WRONG_PASSWORD, "密码错误", 0);
      case 1:
        Admin admin = adminService.getAdminByUsername(loginInfo.get("username"));
        // 将管理员信息写入 session 中
        request.getSession().setAttribute("admin", admin);
        return new Responses<>(ResponseCode.LOGIN_SUCCESS, "登录成功",
            admin.getId());
    }
    return new Responses<>(ResponseCode.FAILED, "系统错误", 0);
  }

  @GetMapping("/{id}")
  public Responses<Admin> queryInfo(@PathVariable Integer id) {
    Admin admin = adminService.getAdminById(id);
    if (admin == null) {
      return new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "管理员不存在", null);
    }
    return new Responses<>(ResponseCode.SUCCESS, "查询成功", admin);
  }

  @PutMapping("/{id}")
  public Responses<Integer> modifyInfo(@PathVariable int id,
      @RequestBody Map<String, String> info) {
    adminService.updateInfo(id, info);
    return new Responses<>(ResponseCode.SUCCESS, "信息修改成功", id);
  }

  @PutMapping("/{id}/password")
  public Responses<String> modifyPassword(@PathVariable int id,
      @RequestBody Map<String, String> passwords) {
    int res = adminService.updatePassword(id, passwords);
    // 根据处理结果返回对应的响应体
    return switch (res) {
      case 0 -> new Responses<>(ResponseCode.WRONG_PASSWORD, "密码错误",
          "");
      case 1 -> new Responses<>(ResponseCode.LOGIN_SUCCESS, "密码修改成功",
          passwords.get("new_password"));
      case -1 -> new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "管理员不存在",
          "");
      default -> new Responses<>(ResponseCode.FAILED, "系统错误", "");
    };
  }
}
