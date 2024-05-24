package com.yuxeng.display.usermodel.Email;

import org.springframework.scheduling.annotation.Scheduled;

public interface EmailService {
  void setMailConfig();

  void sendMail(String email);

  void generateRandomCode();

  Runnable resetCode();

  Runnable testTime();
}
