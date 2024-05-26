package com.yuxeng.display.usermodel;

import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import jakarta.annotation.Resource;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController("/user")  // 前缀api
public class UserController {

  @Resource
  UserService userService;

  @Resource
  UserDao userDao;

  // 第二种log记录器，使用logger.info/warn等等进行记录
  //  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping("/login")
  public Responses<Integer> login(@RequestBody Map<String, String> userRequest) {
    // POST -- LOGIN
    // TODO:使用规范的格式，替代Map<String,String>
    log.info("访问/user/Login");  // TODO:获取访问者的IP
    String username = userRequest.get("username");
    String password = userRequest.get("password");

    // 所有的检测都在Service中完成
    return switch (userService.loginUser(username, password)) {
      case Config.USERNAME_ERROR ->
          new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "Wrong Username", 0);
      case Config.PASSWORD_ERROR ->
          new Responses<>(ResponseCode.WRONG_PASSWORD, "Wrong Password", 0);
      case Config.SUCCESS -> new Responses<>(ResponseCode.SUCCESS, "Login Success", 1);
      default -> {
        log.warn("从理论上来说，它是运行不到这个语句的。除非你在酒吧点了一份炒饭");
        yield null;
      }
    };
  }

  @PostMapping("/send-code") // 忘记密码
  public Responses<Integer> sendMail(@RequestBody String userRequest) {
    // POST -- 找回密码
    // 填写邮箱并获取验证码
    return switch (userService.resetPasswordSendMail(userRequest)) {
      case Config.USERNAME_ERROR ->
          new Responses<>(ResponseCode.ACCOUNT_NOT_EXIST, "Wrong Username", 0);
      case Config.FAIL -> new Responses<>(ResponseCode.FAILED, "Unknown ERROR , Check logs", 0);
      case Config.SUCCESS -> new Responses<>(ResponseCode.SUCCESS, "Send Mail Success", 1);
      default -> {
        log.warn("前面的区域以后再来探险吧");
        yield null;
      }
    };
  }

  @PostMapping("/vertify-code")
  public Responses<Integer> vertifyCode(@RequestBody String userRequest) {
    // POST -- 验证密码
    // 验证邮箱验证码
    if (userService.vertifyCode(userRequest).equals(Config.SUCCESS)) {
      // TODO:这里应该要跳转到指定用户的修改密码页面-->返回的result为id
      return new Responses<>(ResponseCode.SUCCESS, "Send Mail Success", 1);
    }
    return new Responses<>(ResponseCode.FAILED, "Code Error", 0);
  }

  @PutMapping("/{id}/reset-password")
  public Responses<Integer> updatePassword(@PathVariable Long id,
      @RequestBody Map<String, String> userRequset) {
    // PUT -- 验证邮箱验证码并更改密码

    String password = userRequset.get("password");

    if (userService.resetPassword(id, password).equals(Config.SUCCESS)) {
      return new Responses<>(ResponseCode.SUCCESS, "Success", 1);
    }
    return new Responses<>(ResponseCode.FAILED, "Error", 0);
  }

  @PostMapping("/register")
  public Responses<Integer> registerUser(@RequestBody Map<String, String> userRequest) {
    String username = userRequest.get("username");
    String password = userRequest.get("passoword");
    String name = userRequest.get("name");
    String gender = userRequest.get("gender");
    String phone = userRequest.get("phone");
    String email = userRequest.get("email");

    if (userService.registerUser(username, password, name, gender, phone, email)
        .equals(Config.SUCCESS)) {
      return new Responses<>(ResponseCode.SUCCESS, "Success", 1);
    }
    return new Responses<>(ResponseCode.FAILED, "Error", 0);
  }


  @GetMapping("/{id}")
  public Responses<Integer> getUserInfo(@PathVariable Long id) {
    User user = userDao.getUserById(id);
    return null; // TODO
  }

  @PutMapping("/user/{id}")
  public Responses<Integer> updateUserInfo(@PathVariable Long id) {
    User user = userDao.getUserById(id);
    return null;  // TODO
  }
}

