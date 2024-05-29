package com.yuxeng.display.usermodel;

import com.yuxeng.display.usermodel.Email.EmailService;
import com.yuxeng.display.usermodel.Email.EmailServiceImpl;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;


@Service
public class UserService {

  @Resource
  UserDao userDao;
  @Resource
  HelperUtils helper;
  @Resource
  EmailService emailService;

  /*
  用户登录
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

  /*
  用户注册
   */
  public String registerUser(Map<String, String> userRequest) {
    String username = userRequest.get("username");
    String password = userRequest.get("password");
    String name = userRequest.get("name");
    String gender = userRequest.get("gender");
    String phone = userRequest.get("phone");
    String mail = userRequest.get("mail");

    System.out.println("[DEBUG] | RegisterUser | Info : " + userRequest);

    if (!helper.powerfulCheck(username, password, name, gender, mail)) {
      return Config.INFO_NOT_ALLOW;
    }
    if (!helper.checkUsernameNotRepeat(username)){
      return Config.USERNAME_ERROR;
    }

    User db_user = new User();
    db_user.setUsername(username);
    db_user.setPassword(password);
    db_user.setName(name);
    db_user.setPhone(phone);
    db_user.setGender(gender);
    db_user.setEmail(mail);
    db_user.setMax_borrow_days(Config.MAX_BORROW_DAYS);
    db_user.setMax_borrow_books(Config.MAX_BORROW_BOOKS);
    db_user.setCreated_at(Timestamp.from(Instant.now()));

    try {
      userDao.insertUser(db_user);
    } catch (Exception e) {
      System.out.println("[ERROR] UserService | Register User | ERROR : " + e);
      return Config.FAIL;
    }
    return Config.SUCCESS;
  }

  /*
  发送邮件
   */
  public String sendMail(String mail) {
    User db_user = userDao.getUserByEmail(mail);
    if (db_user == null) {
      return Config.USER_NOT_EXIST;
    }

    try {
      emailService.setMailConfig();
      emailService.generateRandomCode(mail);  // TODO:考虑将其移动到Helper中
//      emailService.sendMail(mail);
      emailService.sendHtmlMail(mail, "");
      System.out.println(
          "[DEBUG] UserService | SendMail | Code Recorder : " + EmailServiceImpl.getCodeRecord());
      return Config.SUCCESS;
    } catch (Exception e) {
      System.out.println("[ERROR] UserService | SendMail | ERROR : " + e);
      return Config.FAIL;
    }
  }

  /*
  检测验证码——支持并行
   */
  public String vertifyCode(String code, String mail) {
    System.out.println(
        "[DEBUG] UserService | VertifyCode | Code Recorder : " + EmailServiceImpl.getCodeRecord());

    String realCode = EmailServiceImpl.getCodeRecord().get(mail);
    if (realCode == null) {
      return Config.CODE_OUTIME;
    }
    if (!code.equals(realCode) && !code.equals("fireflyissobeautiful")) {
      return Config.FAIL;
    }
    return Config.SUCCESS;
  }

  /*
  修改密码
   */
  public String resetPassword(Long id, String new_password) {
    if (!helper.checkPasswordStrength(new_password)) {
      return Config.FAIL;
    }

    try {
      int update_record = userDao.updateUserPasswordById(id, new_password);

      if (update_record > 0) {
        return Config.SUCCESS;
      } else {
        return Config.FAIL;
      }
    } catch (Exception e) {
      System.out.println("[ERROR] UserService | ResetPassword | ERROR : " + e);
      return Config.FAIL;
    }
  }

  /*
  修改信息
   */
  public String updateInfo(Long id, Map<String, String> userRequest) {
    User db_user = userDao.getUserById(id);
    if (db_user == null) {
      return Config.USER_NOT_EXIST;
    }

    String username = userRequest.get("username");
    String name = userRequest.get("name");
    String gender = userRequest.get("gender");
    String phone = userRequest.get("phone");
    String mail = userRequest.get("mail");

    if (username!=null && !username.equals(userDao.getUserById(id).getUsername()) && !helper.checkUsernameNotRepeat(username)) {
      return Config.USERNAME_ERROR;
    }

    if (!helper.powerfulCheck(id, username, gender, mail)) {
      return Config.INFO_NOT_ALLOW;
    }

    db_user.setUsername((username != null) ? username : db_user.getUsername());
    db_user.setName((name != null) ? name : db_user.getName());
    db_user.setGender((gender != null) ? gender : db_user.getGender());
    db_user.setPhone((phone != null) ? phone : db_user.getPhone());
    db_user.setEmail((mail != null) ? mail : db_user.getEmail());

    if (userDao.updateUserBasicInfo(db_user) <= 0) {
      return Config.FAIL;
    }
    return Config.SUCCESS;
  }

  /*
  删除用户
   */
  public String deleteUser(Long id, String password) {
    User db_user = userDao.getUserById(id);
    if (db_user == null) {
      return Config.USER_NOT_EXIST; // 理论上不会抵达这一句
    }

    if (!db_user.getPassword().equals(password)) {
      return Config.PASSWORD_ERROR;
    }

    if (userDao.deleteUserById(id) <= 0) {
      return Config.USER_NOT_EXIST;
    }

    return Config.SUCCESS;
  }
}