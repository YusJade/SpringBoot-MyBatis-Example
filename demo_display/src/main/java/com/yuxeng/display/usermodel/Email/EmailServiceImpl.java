package com.yuxeng.display.usermodel.Email;

import java.util.Arrays;
import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

  @Override
  public void sendEmail(EmailDto emailDto) {
    // 设置邮箱
    MailAccount account = new MailAccount();
    account.setHost(EmailConfig.HOST);
    account.setPort(EmailConfig.PORT);
    account.setFrom(EmailConfig.USERNAME + "<" + EmailConfig.EMAIL + ">");
    account.setUser(EmailConfig.USERNAME);
    account.setPass(EmailConfig.PASSWORD);
    account.setAuth(true);  // 授权设置
    account.setSslEnable(true); // ssl方式
    account.setStarttlsEnable(true);  // tls认证

    // [DEBUG]设置邮箱测试
    emailDto.setTos(Arrays.asList("3524506658@qq.com"));
    emailDto.setSubject("Email-Code");
    emailDto.setContent("Coding");

    // 启动！
    try {
      int size = emailDto.getTos().size();  // 发送人列表数
      Mail.create(account).setTos(emailDto.getTos().toArray(new String[size]))
          .setTitle(emailDto.getSubject())
          .setContent(emailDto.getContent())
          .setHtml(true).setUseGlobalSession(false).send();

    } catch (Exception e) {
      System.out.println("我并不认为这里会报错\n" + e);
      System.out.println("emailDto : " + emailDto.getTos() + " | " + emailDto.getSubject() + " | "
          + emailDto.getContent());
    }
  }
}
