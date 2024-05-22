package com.yuxeng.display.usermodel;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelperUtils {

  /*  TODO：检测机制——规划

      email：
      2.可以用作忘记密码时的验证手段
   */
  @Resource
  UserDao userDao;
  @Resource
  Config cfg;

  public boolean checkUsernameDuplicate(String username) {
    return userDao.getUserByUsername(username) == null;
  }

  public boolean checkUsernameValidity(String username) {
    return username.matches(cfg.USERNAME_REGEX) && !username.equals("admin")
        && username.length() <= 50;
  }

  public boolean checkPasswordStrength(String password){
    return password.matches(cfg.PASSWORD_REGEX) && password.length() <= 50;
  }

  public boolean checkEmailValidity(String email){
    return email.matches(cfg.EMAIL_REGEX);
  }

  public boolean checkGenderValidity(String gender){
    return cfg.GENDER_LIST.contains(gender);
  }
}
