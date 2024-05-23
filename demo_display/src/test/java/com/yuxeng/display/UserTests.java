package com.yuxeng.display;

import com.yuxeng.display.usermodel.Email.AuthServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class UserTests {

  @Resource
  private AuthServiceImpl authServiceImpl;

  @Resource
  JavaMailSenderImpl javaMailSender;

  @Test
  void checkSendEmailStatus() {
    authServiceImpl.sendEmailCode("3524506658@qq.com");
  }

  @Test
  void simpleSendEmailTest() {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Test");
    message.setText("Testing");
    message.setTo("3524506658@qq.com");
    message.setFrom("LuluDominic97@outlook.fr");

    javaMailSender.send(message);
  }
}
