package com.yuxeng.display.usermodel;

import com.yuxeng.display.usermodel.Email.EmailService;
import com.yuxeng.display.usermodel.Email.EmailServiceImpl;
import jakarta.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
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
  @Resource
  EmailService emailService;

  /**
   * 登陆检测
   * @param username 用户名
   * @param password 密码
   * @return String / boolean  // 是否需要提供哪个填写出问题了 MARK
   */
  public boolean loginUser(String username, String password) {
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
    // POST —— 已经过验证
    // TODO:可以改为返回string类型，提示报什么错
    // TODO:insertUser在失败时会报什么类型的错
    if (!helper.checkUsernameValidity(username) || !helper.checkPasswordStrength(password)
        || !helper.checkGenderValidity(gender) || !helper.checkEmailValidity(email)) {
      return cfg.FAIL;
    }

    User db_user = new User();
    db_user.setUsername(username);
    db_user.setPassword(password);
    db_user.setName(name);
    db_user.setPhone(phone);
    db_user.setGender(gender);
    db_user.setEmail(email);
    db_user.setMax_borrow_days(cfg.MAX_BORROW_DAYS);
    db_user.setMax_borrow_books(cfg.MAX_BORROW_BOOKS);
    db_user.setCreated_at(Timestamp.from(Instant.now()));
    try {
      int db_id = userDao.insertUser(db_user);
      // DEBUG
      System.out.println("Insert DB_ID : " + db_id);

    } catch (Exception e) {
      System.out.println(e);
      return cfg.FAIL;
    }
    return cfg.SUCCESS;
  }


  public boolean resetPasswordSendMail(String username) {
    // POST——已通过验证
    // TODO：查询登陆状态——应该在Controller里查询/写重载函数
    User db_user = userDao.getUserByUsername(username);
    if (db_user == null) {
      return cfg.FAIL;
    }
    String db_email = db_user.getEmail();

    try {
      emailService.setMailConfig();
      emailService.generateRandomCode();  // TODO:考虑将其移动到Helper中
      emailService.sendMail(db_email);
      return true;
    } catch (Exception e) {
      System.out.println("ERROR : " + e);
      return false;
    }
  }

  public boolean resetPasswordCheckCode(String code) {
    // POST——已通过验证
    return code.equals(EmailServiceImpl.code);
  }


  /**
   * 修改密码——登陆状态
   *
   * @param username     昵称-String
   * @param new_password 新密码-String
   * @return String
   */
  public boolean resetPasswordWOCheck(String username, String new_password) {
    // PORT——已通过验证
    if (!helper.checkPasswordStrength(new_password)) {
      System.out.println("总不可能是这里");
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
      System.out.println("ERROR : "+e);
      return cfg.FAIL;
    }
  }
}