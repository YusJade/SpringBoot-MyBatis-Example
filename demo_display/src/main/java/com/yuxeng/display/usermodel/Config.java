package com.yuxeng.display.usermodel;

import java.util.Arrays;
import java.util.List;

public class Config {

  /* 值 */
  static Long MAX_BORROW_DAYS = 30L;
  static Long MAX_BORROW_BOOKS = 10L;

  /* 正则表达式 */
  static String USERNAME_REGEX = "[a-zA-Z][a-zA-Z0-9_]*$";  // Username起名规则
  static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])\\S{8,}$"; // Password
  static String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

  /* 匹配列表 */
  static List<String> GENDER_LIST = Arrays.asList("男", "女", "男性", "女性", "男人", "女人",
      "沃尔玛购物袋", "武装直升机", "man", "lady", "MAN", "LADY", "Lady", "Man");

  /* 返回 */
  public static final String SUCCESS = "SUCCESS";
  public static final String FAIL = "FAIL";
  public static final String USERNAME_ERROR = "USERNAMEERROR";
  public static final String PASSWORD_ERROR = "PASSWORDERROR";
  public static final String USER_NOT_EXIST = "USERNOTEXIST";
  public static final String INFO_NOT_ALLOW = "INFONOTALLOW";
  public static final String CODE_OUTIME = "CODEOUTIME";
}