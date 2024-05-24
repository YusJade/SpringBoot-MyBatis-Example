package com.yuxeng.display.usermodel;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController("/user")  // 前缀api
public class UserController {

  @Resource
  UserService userService;

  @PostMapping("/login")
  public Integer login(@RequestBody Map<String, String> userRequest) {
    // POST -- LOGIN
    // TODO:使用规范的格式，替代Map<String,String>
    String username = userRequest.get("username");
    String password = userRequest.get("password");

    // 所有的检测都在Service中完成
    if (userService.loginUser(username, password)) {
      return 1; // TODO:规范
    } else {
      return 0;
    }
  }

  @PostMapping("/forget") // 忘记密码
  public Integer forget(@RequestBody String userRequest) {
    // POST -- 在未登录状态下忘记密码
    // 填写邮箱并获取验证码
    String username = userRequest;

    if (userService.resetPasswordSendMail(username)) {
      return 1;
    } else {
      return 0;
    }
  }

  @PostMapping("/updatepassword")
  public Integer updatePassword(@RequestBody Map<String,String> userRequset) {
    // PUT -- 验证邮箱验证码并更改密码
    String username = userRequset.get("username");
    String password = userRequset.get("password");
    String mailCode = userRequset.get("mailcode");

    if (userService.resetPasswordCheckCode(mailCode)) {
      if (userService.resetPasswordWOCheck("username", "password")) {
        return 1;
      }
    }
    return 0;
  }

  @PostMapping("/register")
  public Integer registerUser(@RequestBody Map<String,String> userRequest){
    return 1;
  }

}
