package com.yuxeng.display.usermodel;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledFuture;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;


@Service
public class HelperUtils {

  @Resource
  UserDao userDao;

  private ThreadPoolTaskScheduler taskScheduler;

  private ScheduledFuture<?> scheduledFuture;

  /* INIT */
  @PostConstruct // 自动调用——在注入时
  private void taskSchedulerInit() {
    taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.setThreadNamePrefix("Task-");
    taskScheduler.initialize();
  }

  /*
  检测用户名是否重复
   */
  public boolean checkUsernameRepeat(String username) {
    return userDao.getUserByUsername(username) == null;
  }

  /*
  检测用户名是否符合要求
   */
  public boolean checkUsernameValidity(String username) {
    System.out.println("DEBUG : Username");
    return username.matches(Config.USERNAME_REGEX) && !username.equals("admin")
        && username.length() <= 50;
  }

  /*
  检测密码强度
   */
  public boolean checkPasswordStrength(String password) {
    System.out.println("DEBUG : Password");
    return password.matches(Config.PASSWORD_REGEX) && password.length() <= 50;
  }

  /*
  检测邮箱是否符合要求
   */
  public boolean checkEmailValidity(String email) {
    System.out.println("DEBUG : Email");
    return email.matches(Config.EMAIL_REGEX);
  }

  /*
  检测性别是否符合要求
   */
  public boolean checkGenderValidity(String gender) {
    System.out.println("DEBUG : Gender");
    return Config.GENDER_LIST.contains(gender);
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
    if (scheduledFuture == null || scheduledFuture.isCancelled()) {
      scheduledFuture = taskScheduler.schedule(scheduledFunc, Instant.now().plus(
          Duration.ofSeconds(waitTime)));
    }
  }
}
