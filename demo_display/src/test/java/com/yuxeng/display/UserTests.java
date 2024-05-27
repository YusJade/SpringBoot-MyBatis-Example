package com.yuxeng.display;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.yuxeng.display.usermodel.Config;
import com.yuxeng.display.usermodel.Email.EmailService;
import com.yuxeng.display.usermodel.HelperUtils;
import com.yuxeng.display.usermodel.UserDao;
import com.yuxeng.display.usermodel.UserService;
import jakarta.annotation.Resource;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class UserTests {

  @Resource
  private EmailService emailService;

  @Resource
  private HelperUtils helper;

  @Resource
  private UserDao userDao;

  @Resource
  private UserService userService;

  @MockBean
  private Scanner scanner;

  @Test
  public void sendMailTest() {
    emailService.setMailConfig();
    emailService.sendMail("35245906658@qq.com");
  }

//  @Test
//  public void waitTimeTestNotWhile() {
//    helper.startScheduledTask(emailService.testTime(), 10);
//    System.out.println("End");
//  }
//
//  @Test
//  public void waitTimeTestWithWhile() {
//    helper.startScheduledTask(emailService.testTime(), 5);
//
//    // 需要while保持@Test的活性，定时任务在另外一个线程里
//    while (true) {
//      int x = 1;
//    }
//  }

  @Test
  void resetPasswordWOCheckTest(){
    String username = "Firefly";
    String password = "1234Qwert12345";
    userService.resetPassword(1L,password);
  }
//  @Test
//  void addUser() {
//    String username = "Firefly";
//    String password = "Abcdef12";
//    String name = "SAM";
//    String gender = "lady";
//    String phone = "10086";
//    String email = "3524506658@qq.com";
//    if (userService.registerUser(username).equals(Config.SUCCESS)) {
//      System.out.println("Insert Success");
//    } else {
//      System.out.println(helper.checkUsernameValidity(username));
//      System.out.println(helper.checkPasswordStrength(password));
//      System.out.println(helper.checkGenderValidity(gender));
//      System.out.println(helper.checkEmailValidity(email));
//    }
//  }

//  @Test
//  void resetPasswordSendMailTest() {
//    String username = "Firefly";
//    if (userService.sendMail(username).equals(Config.SUCCESS)) {
//      System.out.println("Send Email Success");
//    }
//
//    String code = "123456";
////    try {
////      Thread.sleep(20000); // 暂停20秒，测试验证码过期
////    }catch (Exception e) {
////      System.out.println("Stop ERROR");
////    }
//
//    if (userService.vertifyCode(code).equals(Config.SUCCESS)) {
//      System.out.println("Check Code Success");
//
//      if (userService.resetPassword(1L, "Test1234Test").equals(Config.SUCCESS)) {
//        System.out.println("Change Password Success");
//      }
//    }
//  }

}
