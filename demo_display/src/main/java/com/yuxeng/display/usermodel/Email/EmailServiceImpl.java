package com.yuxeng.display.usermodel.Email;

import com.yuxeng.display.usermodel.HelperUtils;
import jakarta.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

  // 邮箱--验证码
  // ConcurrentHashMap允许并发访问
  // 使用getter封装map
  // 为什么有static还要使用@Getter了？————问就是偶尔会出现奇妙的线程问题
  @Getter
  public static Map<String, String> codeRecord = new ConcurrentHashMap<>();

  @Resource
  JavaMailSenderImpl mailSender;  // 邮件对象
  @Resource
  HelperUtils helper;

  /*
  邮箱发送者配置
   */
  @Override
  public void setMailConfig() {
    // 使用STARTTLS验证
    mailSender.setHost(EmailConfig.HOST);
    mailSender.setPort(EmailConfig.PORT);
    mailSender.setUsername(EmailConfig.USERNAME);
    mailSender.setPassword(EmailConfig.PASSWORD);
    mailSender.getJavaMailProperties().put("mail.smtp.ssl.enable", "true");
    mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
  }

  /*
  发送邮件
   */
  @Override
  public void sendMail(String mail) {
    // 发送邮件 TODO:有空整个邮箱列表，循环至可连接邮箱
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Your Code");
    message.setText(codeRecord.get(mail));
    message.setFrom(EmailConfig.EMAIL);
    message.setTo(mail);

    mailSender.send(message); // 发送
  }

  /*
  生成随机六位验证码并载入
   */
  @Override
  public void generateRandomCode(String mail) {
    String sources = EmailConfig.SOURCES;  // 随机字符组合
    Random random = new Random();  // 随机器
    StringBuilder flag = new StringBuilder(); // 随机字符串 [String在这里可以替代StringBuilder，但Builder性能更好]

    for (int i = 0; i < EmailConfig.CODELEN; ++i) {
      // 每一次循环从sources中获取一位并加入到flag中
      flag.append(sources.charAt(random.nextInt(sources.length() - 1)));
    }

    codeRecord.put(mail, flag.toString()); // 记录
    System.out.println("[DEBUG] EmailService | GenerateRandomCode | Code Recorder : " + codeRecord);
    // 对验证码定时重置，第二个参数为等待时间(S)，默认10min
    helper.startScheduledTask(resetCode(mail), EmailConfig.CODETIME); // 启动定时器
  }

  /*
  指定邮箱的验证码定时过时器
   */
  @Override
  public Runnable resetCode(String mail) {
    // 该任务是定时任务——CODETIME
    return () -> {
      System.out.println(
          "[DEL] EmailService | ResetCode | CodeRecorder : " + codeRecord.get(mail)); // DEBUG:查看剩余有效验证码列表
      codeRecord.remove(mail);
    };
  }
}
