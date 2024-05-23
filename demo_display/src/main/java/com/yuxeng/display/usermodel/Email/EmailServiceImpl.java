package com.yuxeng.display.usermodel.Email;

import com.yuxeng.display.usermodel.HelperUtils;
import jakarta.annotation.Resource;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

  public String code;  // 临时验证码
  @Resource
  JavaMailSenderImpl mailSender;  // 邮件对象
  @Resource
  HelperUtils helper;

  @Override
  public void setMailConfig(){
    // 使用STARTTLS验证
    mailSender.setHost(EmailConfig.HOST);
    mailSender.setPort(EmailConfig.PORT);
    mailSender.setUsername(EmailConfig.USERNAME);
    mailSender.setPassword(EmailConfig.PASSWORD);
    mailSender.getJavaMailProperties().put("mail.smtp.ssl.enable", "true");
    mailSender.getJavaMailProperties().put("mail.smtp.auth","true");
  }

  @Override
  public void sendMail() {
    // 发送邮件
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Your Code");
    message.setText(this.code);
    message.setFrom(EmailConfig.EMAIL);
    message.setTo("3524506658@qq.com");

    mailSender.send(message);
  }

  @Override
  public void generateRandomCode(){
    String sources = EmailConfig.SOURCES;  // 随机字符组合
    Random random = new Random();  // 随机器
    StringBuilder flag = new StringBuilder(); // 随机字符串 [String在这里可以替代StringBuilder，但Builder性能更好]

    for (int i=0;i<6;++i) {
      // 每一次循环从sources中获取一位并加入到flag中
      flag.append(sources.charAt(random.nextInt(sources.length()-1)));
    }

    code = flag.toString();
    helper.startScheduledTask(resetCode(), EmailConfig.CODETIME); // 启动定时器
  }

  @Override
  public Runnable resetCode(){
    // 该任务是定时任务——10min
    code = null;
    helper.stopScheduledTask(); // 关闭定时器
    return null;
  }

  @Override
  public Runnable testTime(){
    System.out.println("Test");

    // 返回一个新的 Runnable 实例
    return () -> {
      System.out.println("Here");
    };
  }
}
