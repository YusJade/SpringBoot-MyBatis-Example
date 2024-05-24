package com.yuxeng.display.usermodel;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledFuture;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;


@Service
public class HelperUtils {

  /*  TODO：检测机制——规划

   */
  @Resource
  UserDao userDao;
  @Resource
  Config cfg;

  private ThreadPoolTaskScheduler taskScheduler;

  private ScheduledFuture<?> scheduledFuture;

  /* INIT */
  @PostConstruct // 自动调用——在注入时
  private void taskSchedulerInit(){
    taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.setThreadNamePrefix("Task-");
    taskScheduler.initialize();
  }

  public boolean checkUsernameDuplicate(String username) {
    return userDao.getUserByUsername(username) == null;
  }

  public boolean checkUsernameValidity(String username) {
    return username.matches(cfg.USERNAME_REGEX) && !username.equals("admin")
        && username.length() <= 50;
  }

  public boolean checkPasswordStrength(String password){
    return !password.matches(cfg.PASSWORD_REGEX) || password.length() > 50;
  }

  public boolean checkEmailValidity(String email){
    return email.matches(cfg.EMAIL_REGEX);
  }

  public boolean checkGenderValidity(String gender){
    return cfg.GENDER_LIST.contains(gender);
  }

  public void stopScheduledTask() {
    // 取消定时任务
    if (scheduledFuture != null) {
      scheduledFuture.cancel(true);
      scheduledFuture = null;
    }
  }

  /**
   * 对传入的方法启动定时
   * @param scheduledFunc 需要开启定时任务的函数
   * @param waitTime  等待时间(min)
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
