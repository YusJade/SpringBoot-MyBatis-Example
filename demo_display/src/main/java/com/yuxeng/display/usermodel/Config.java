package com.yuxeng.display.usermodel;

import java.util.Arrays;
import java.util.List;

public class Config {

  /* 值 */
  static Long MAX_BORROW_DAYS = 30L;
  static Long MAX_BORROW_BOOKS = 10L;

  /* 正则表达式 */
  /*
    https://blog.csdn.net/weixin_43860260/article/details/91417485
   */
  static String USERNAME_REGEX = "[a-zA-Z][a-zA-Z0-9_]*$";  // Username起名规则
  static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])\\S{8,}&"; // Password
  static String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

  /* 匹配列表 */
  static List<String> GENDER_LIST = Arrays.asList("男", "女", "男性", "女性", "男人", "女人",
      "沃尔玛购物袋", "武装直升机", "man", "lady", "MAN", "LADY");

  /* 返回 */
  static final String SUCCESS = "SUCCESS";
  static final String FAIL = "FAIL";
  static final String USERNAME_ERROR = "USERNAMEERROR";
  static final String PASSWORD_ERROR = "PASSWORDERROR";
  static final String
}