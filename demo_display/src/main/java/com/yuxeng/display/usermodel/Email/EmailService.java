package com.yuxeng.display.usermodel.Email;

import java.util.Map;

public interface EmailService {

  void setMailConfig();

  void sendMail(String mail);

  void sendHtmlMail(String mail, String filePath);

  void generateRandomCode(String mail);

  Runnable resetCode(String mail);

}
