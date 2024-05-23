package com.yuxeng.display.usermodel;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  /*
  TODO:
  1.完善返回清单

   */
  @Resource
  UserDao userDao;
  @Resource
  Config cfg;
  @Resource
  HelperUtils helper;

  /**
   * 登陆检测
   *
   * @param username 用户名
   * @param password 密码
   * @return String / boolean  // 是否需要提供哪个填写出问题了 MARK
   */
  public boolean validateUser(String username, String password) {
    User db_user = userDao.getUserByUsername(username);
    if (db_user == null) {
      return cfg.FAIL;  // TODO:是否需要转为json-->Controller层再写 | 将错误信息统一管理
    }

    if (!password.equals(db_user.getPassword())) {
      return cfg.FAIL;
    }

    return cfg.SUCCESS;
  }

  /**
   * 用户注册
   *
   * @param username String-昵称
   * @param password String-密码
   * @param name     String-真实姓名
   * @param gender   String-性别
   * @param phone    String-手机号
   * @param email    String-邮箱
   * @return boolean
   */
  public boolean registerUser(String username, String password, String name, String gender,
      String phone, String email) {
    // TODO:可以改为返回string类型，提示报什么错
    if (!helper.checkUsernameValidity(username) || helper.checkPasswordStrength(password)
        || !helper.checkGenderValidity(gender) || !helper.checkEmailValidity(email)) {
      return cfg.FAIL;
    }

    // TODO:insertUser在失败时会报什么类型的错
    try {
      int db_id = userDao.insertUser(username, password, name, gender, phone, email,
          cfg.MAX_BORROW_DAYS,
          cfg.MAX_BORROW_BOOKS);
      // DEBUG
      System.out.println("Insert DB_ID : " + db_id);

    } catch (Exception e) {
      return cfg.FAIL;
    }
    return cfg.SUCCESS;
  }


  public boolean resetPassword(String username) {
    // TODO：查询登陆状态——应该在Controller里查询/写重载函数
    // TODO：如果未登录，要使用什么来验证
    // TODO：邮箱


    return true;
  }

  /**
   * 修改密码——登陆状态
   * @param username     昵称-String
   * @param new_password 新密码-String
   * @return String
   */
  public boolean resetPassword(String username, String new_password) {
    if (helper.checkPasswordStrength(new_password)) {
      return cfg.FAIL;
    }

    try {
      int update_record = userDao.updateUserPasswordByUsername(username, new_password);

      if (update_record > 0) {
        return cfg.SUCCESS;
      } else {
        return cfg.FAIL;
      }
    } catch (Exception e) {
      return cfg.FAIL;
    }
  }
}