package com.yuxeng.display.usermodel;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HelperUtils {

  @Resource
  UserDao userDao;

  private ThreadPoolTaskScheduler taskScheduler;

  private ScheduledFuture<?> scheduledFuture;

  /* INIT */
  @PostConstruct // 自动调用——在注入时
  private void taskSchedulerInit() {
    taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);  // 线程池
    taskScheduler.setThreadNamePrefix("Task-");
    taskScheduler.initialize();
  }

  /*
  检测用户名是否重复——重复false/不重复true
   */
  public boolean checkUsernameNotRepeat(String username) {
    log.info(String.format("验证用户名 %s 是否重复", username));
//    System.out.println("[DEBUG] HelperUtils | CheckUsernameRepeat : " + username);
    return userDao.getUserByUsername(username) == null;
  }

  /*
  检测用户名是否符合要求
   */
  public boolean checkUsernameValidity(String username) {
    log.info(String.format("验证用户名 %s 是否合法", username));
//    System.out.println("[DEBUG] HelperUtils | CheckUsernameValidity : " + username);
    return username.matches(Config.USERNAME_REGEX) && !username.equals("admin")
        && username.length() <= 50;
  }

  /*
  检测密码强度
   */
  public boolean checkPasswordStrength(String password) {
    log.info("验证用户密码是否符合强度要求");
//    System.out.println("[DEBUG] HelperUtils | CheckPasswordStrength : " + password);
    return password.matches(Config.PASSWORD_REGEX) && password.length() <= 50;
  } // 无需反转

  /*
  检测邮箱是否符合要求
   */
  public boolean checkEmailValidity(String email) {
    log.info(String.format("验证邮箱 %s 是否合法", email));
//    System.out.println("[DEBUG] HelperUtils | CheckEmailValidity : " + email);
    return email.matches(Config.MAIL_REGEX);
  }

  /*
  检测性别是否符合要求
   */
  public boolean checkGenderValidity(String gender) {
    log.info(String.format("验证性别 %s 是否合法", gender));
//    System.out.println("[DEBUG] HelperUtils | CheckGenderValidity : " + gender);
    return Config.GENDER_LIST.contains(gender);
  }

  /*
  融合检测——未注册用户
   */
  public boolean powerfulCheck(String username, String password, String name, String gender, String email) {
    return username != null &&
        password != null &&
        gender != null &&
        name != null &&
        email != null &&
        checkUsernameValidity(username) &&
        checkPasswordStrength(password) &&
        checkGenderValidity(gender) &&
        checkEmailValidity(email);

  }


  /*
  融合检测——已注册用户
   */
  public boolean powerfulCheck(Long id, String username, String gender, String email) {
    return (username == null || checkUsernameValidity(username)) &&
        (username == null || username.equals(userDao.getUserById(id).getUsername()))
        || checkUsernameNotRepeat(username) &&
        (gender == null || checkGenderValidity(gender)) &&
        (email == null || checkEmailValidity(email));
  }

  /*
  [DEBUG] 取消所有线程的定时任务
   */
  public void stopScheduledTask() {
    if (scheduledFuture != null) {
      scheduledFuture.cancel(true);
      scheduledFuture = null;
    }
  }

  /*
  对传入的方法启用定时器
   */
  public void startScheduledTask(Runnable scheduledFunc, int waitTime) {
    // 启动定时任务
    // 解析：使用taskScheduler创建定时器
    // Instant.now()获取当前时间，plus加上waitTime得到结束时间
    System.out.println(
        "[DEBUG] | HelperUtils | StartScheduledTask | The Task Added : " + scheduledFunc
            + " | Time : " + waitTime + " S ");
    if (scheduledFuture == null || scheduledFuture.isCancelled()) {
      scheduledFuture = taskScheduler.schedule(scheduledFunc, Instant.now().plus(
          Duration.ofSeconds(waitTime)));
    }
  }
}
