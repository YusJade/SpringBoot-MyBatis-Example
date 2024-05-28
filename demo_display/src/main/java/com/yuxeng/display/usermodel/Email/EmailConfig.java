/**
 * 本文件用于配置邮件相关设置
 */
package com.yuxeng.display.usermodel.Email;

public class EmailConfig {

  public static String EMAIL = "eigb903@sinpor.top";
  public static String HOST = "smtp.sinpor.top";
  public static int PORT = 465;
  public static String USERNAME = "eigb903@sinpor.top";
  public static String PASSWORD = "sinpor123";

  public static String SOURCES = "0123456789";  // 验证码取值范围
  public static final int CODETIME = 600; // 计时器时间(s)
  public static final int CODELEN = 6; // 验证码长度
}
