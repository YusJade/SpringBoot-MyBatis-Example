package com.yuxeng.display.usermodel;

import com.yuxeng.display.usermodel.Email.EmailService;
import com.yuxeng.display.usermodel.Email.EmailServiceImpl;
import jakarta.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  /*
  TODO:
  1.完善返回清单


   */
  @Resource
  UserDao userDao;
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
  public String loginUser(String username, String password) {
    User db_user = userDao.getUserByUsername(username);
    if (db_user == null) {
      return Config.USERNAME_ERROR;
    }

    if (!password.equals(db_user.getPassword())) {
      return Config.PASSWORD_ERROR;
    }

    return Config.SUCCESS;
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
  public String registerUser(String username, String password, String name, String gender,
      String phone, String email) {
    // POST —— 已经过验证
    // TODO:可以改为返回string类型，提示报什么错
    // TODO:insertUser在失败时会报什么类型的错
    if (!helper.checkUsernameValidity(username) || !helper.checkPasswordStrength(password)
        || !helper.checkGenderValidity(gender) || !helper.checkEmailValidity(email)) {
      return Config.FAIL;
    }

    User db_user = new User();
    db_user.setUsername(username);
    db_user.setPassword(password);
    db_user.setName(name);
    db_user.setPhone(phone);
    db_user.setGender(gender);
    db_user.setEmail(email);
    db_user.setMax_borrow_days(Config.MAX_BORROW_DAYS);
    db_user.setMax_borrow_books(Config.MAX_BORROW_BOOKS);
    db_user.setCreated_at(Timestamp.from(Instant.now()));
    try {
      int db_id = userDao.insertUser(db_user);
      // DEBUG
      System.out.println("Insert DB_ID : " + db_id);

    } catch (Exception e) {
      System.out.println(e);
      return Config.FAIL;
    }
    return Config.SUCCESS;
  }


  public String resetPasswordSendMail(String username) {
    // POST——已通过验证
    // TODO：查询登陆状态——应该在Controller里查询/写重载函数
    User db_user = userDao.getUserByUsername(username);
    if (db_user == null) {
      return Config.USERNAME_ERROR;
    }
    String db_email = db_user.getEmail();

    try {
      emailService.setMailConfig();
      emailService.generateRandomCode();  // TODO:考虑将其移动到Helper中
      emailService.sendMail(db_email);
      return Config.SUCCESS;
    } catch (Exception e) {
      log.error("| Reset Password Send Mail | : ",e);
      return Config.FAIL;
    }
  }

  public String vertifyCode(String code) {
    // POST——已通过验证
    if (code.equals(EmailServiceImpl.code)){
      return Config.SUCCESS;
    }
    return Config.FAIL;
  }


  /**
   * 修改密码——登陆状态
   * @param id  id值
   * @param new_password 新密码-String
   * @return String
   */
  public String resetPassword(Long id, String new_password) {
    // PORT——已通过验证
    if (!helper.checkPasswordStrength(new_password)) {
      System.out.println("总不可能是这里");
      return Config.FAIL;
    }

    try {
//      int update_record = userDao.updateUserPasswordByUsername(username, new_password);
        int update_record = userDao.updateUserPasswordById(id,new_password);

      if (update_record > 0) {
        return Config.SUCCESS;
      } else {
        return Config.FAIL;
      }
    } catch (Exception e) {
      System.out.println("ERROR : "+e);
      return Config.FAIL;
    }
  }
}