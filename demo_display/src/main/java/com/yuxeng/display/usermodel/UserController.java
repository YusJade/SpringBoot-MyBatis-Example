package com.yuxeng.display.usermodel;

import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")// 前缀api
public class UserController {

  @Resource
  UserService userService;

  @Resource
  UserDao userDao;

  @PostMapping("/login")
  public Responses<Long> login(HttpServletRequest request,
      @RequestBody Map<String, String> userRequest) {
    // POST -- 用户登录 -- 已验证
    String username = userRequest.get("username");
    String password = userRequest.get("password");

    // 所有的检测都在Service中完成
    return switch (userService.loginUser(username, password)) {
      case Config.USERNAME_ERROR ->
          new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "Wrong Username", null);
      case Config.PASSWORD_ERROR ->
          new Responses<>(ResponseCode.WRONG_PASSWORD, "Wrong Password", null);
      case Config.SUCCESS -> {
        request.getSession()
            .setAttribute("user", userDao.getUserByUsername(username)); // 将登陆信息加入到Session
        yield new Responses<>(ResponseCode.SUCCESS, "Login Success",
            userDao.getUserByUsername(username).getId());
      }
      default -> new Responses<>(ResponseCode.FAILED, "Here is unaccessible", null);
    };
  }

  @PostMapping("/register")
  public Responses<Long> registerUser(@RequestBody Map<String, String> userRequest) {
    // POST -- 注册用户 -- 已验证
    // Finished_TODO: 状态码新增用户名重复 BY YuXeng
    return switch (userService.registerUser(userRequest)) {
      case Config.INFO_NOT_ALLOW ->
          new Responses<>(ResponseCode.BAD_REQUEST, "Input Info Unallow", null);
      case Config.USERNAME_ERROR ->
          new Responses<>(ResponseCode.USERNAME_REPEATED, "Username Repeat", null);
      case Config.FAIL -> new Responses<>(ResponseCode.FAILED, "Register Failed", null);
      case Config.SUCCESS -> new Responses<>(ResponseCode.SUCCESS, "Register Success",
          userDao.getUserByUsername(userRequest.get("username")).getId());
      default -> {
        yield null;
      }
    };
  }

  @PostMapping("/send-code") // 忘记密码
  public Responses<String> sendMail(@RequestBody Map<String, String> userRequest) {
    // POST -- 找回密码Part1
    // 填写邮箱并获取验证码
    return switch (userService.sendMail(userRequest.get("mail"))) {
      case Config.USER_NOT_EXIST ->
          new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "Wrong Email", null);
      case Config.FAIL -> new Responses<>(ResponseCode.FAILED,
          "Mail delivery error, please check SMTP configuration", null);
      case Config.SUCCESS ->
          new Responses<>(ResponseCode.SUCCESS, "Send Mail Success", userRequest.get("mail"));
      default -> {
        yield null;
      }
    };
  }

  @PostMapping("/{mail}/code")
  public Responses<Long> vertifyCode(HttpServletRequest request, @PathVariable String mail,
      @RequestBody Map<String, String> userRequest) {
    // POST -- 验证密码
    // 验证邮箱验证码
    return switch (userService.vertifyCode(userRequest.get("code"), mail)) {
      case Config.CODE_OUTIME -> new Responses<>(ResponseCode.OUT_TIME, "Code Outtime", null);
      case Config.FAIL -> new Responses<>(ResponseCode.FAILED, "Code Error", null);
      case Config.SUCCESS -> {
        request.getSession().setAttribute("user", userDao.getUserByEmail(mail)); // 将登陆信息加入到Session
        System.out.println(userDao.getUserByEmail(mail));
        yield new Responses<>(ResponseCode.SUCCESS, "Vertify Code Success",
            userDao.getUserByEmail(mail).getId());
      }
      default -> {
        yield null;
      }
    };
  }

  @PutMapping("/{id}/password")
  public Responses<Long> updatePassword(@PathVariable Long id,
      @RequestBody Map<String, String> userRequset) {
    // PUT -- 验证邮箱验证码并更改密码

    String password = userRequset.get("password");

    if (userService.resetPassword(id, password).equals(Config.SUCCESS)) {
      return new Responses<>(ResponseCode.SUCCESS, "Success Change", id);
    }
    return new Responses<>(ResponseCode.FAILED, "Not Allowed Password", null);
  }

  @PutMapping("/{id}")
  public Responses<Integer> updateUserInfo(@PathVariable Long id,
      @RequestBody Map<String, String> userRequest) {
    return switch (userService.updateInfo(id, userRequest)) {
      case Config.USER_NOT_EXIST ->
          new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "Can't Find The User", null);
      case Config.USERNAME_ERROR ->
          new Responses<>(ResponseCode.USERNAME_REPEATED, "Username Repeat", null);
      case Config.INFO_NOT_ALLOW -> new Responses<>(ResponseCode.BAD_REQUEST, "INFO UNALLOW", null);
      case Config.FAIL -> new Responses<>(ResponseCode.FAILED, "Update Failed", null);
      case Config.SUCCESS -> new Responses<>(ResponseCode.SUCCESS, "Update Success", 1);
      default -> {
        yield null;
      }
    };
  }

  @GetMapping("/{id}")
  public Responses<User> getUserInfo(@PathVariable Long id) {
    User user = userDao.getUserById(id);
    if (user == null) {
      return new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "Can't Find The User", null);
    }
    return new Responses<>(ResponseCode.SUCCESS, "Get Info Success", user);
  }

  @DeleteMapping("/{id}/deregister")
  public Responses<Integer> deleteUser(@PathVariable Long id,
      @RequestBody Map<String, String> userRequest) {
    return switch (userService.deleteUser(id, userRequest.get("password"))) {
      case Config.USER_NOT_EXIST ->
          new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "Can't Find The User", null);
      case Config.PASSWORD_ERROR ->
          new Responses<>(ResponseCode.WRONG_PASSWORD, "Password Error", null);
      case Config.FAIL ->
          new Responses<>(ResponseCode.FAILED, "Unknown Fail , Check The Log", null);
      case Config.SUCCESS -> new Responses<>(ResponseCode.SUCCESS, "Delete Success", 1);
      default -> {
        yield null;
      }
    };
  }
}

