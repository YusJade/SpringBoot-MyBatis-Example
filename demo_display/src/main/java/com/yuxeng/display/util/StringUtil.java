package com.yuxeng.display.util;

public class StringUtil {
  /**
   * 判断是否为空字符串最优代码
   * @param str 字符串
   * @return 如果为空，则返回true
   */
  public static boolean isEmpty(String str){
    return str == null || str.trim().length() == 0;
  }

  /**
   * 判断字符串是否非空
   * @param str 字符串
   * @return 如果不为空，则返回true
   */
  public static boolean isNotEmpty(String str){
    return !isEmpty(str);
  }
}
