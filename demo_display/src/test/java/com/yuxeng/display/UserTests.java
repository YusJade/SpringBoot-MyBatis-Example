package com.yuxeng.display;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.yuxeng.display.usermodel.Email.EmailService;
import com.yuxeng.display.usermodel.HelperUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class UserTests {
  @Resource
  private EmailService emailService;

  @Resource
  private HelperUtils helper;

  @Test
  public void sendMailTest() {
    emailService.setMailConfig();
    emailService.sendMail();
  }

  @Test
  public void waitTimeTest(){
//    helper.startScheduledTask(emailService.testTime(), 60);
    emailService.testTime();
//    System.out.println("Test");
  }

  @Test
  public void waitTimeTestA() {
    // 调用 testTime 方法并验证其行为
    helper.startScheduledTask(emailService.testTime(), 2);
//    Runnable task = emailService.testTime();

    // 等待一段时间，确保任务被执行
    try {
      Thread.sleep(5000); // 等待 1 秒
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // 添加其他断言或验证逻辑
    // ...
  }

}
