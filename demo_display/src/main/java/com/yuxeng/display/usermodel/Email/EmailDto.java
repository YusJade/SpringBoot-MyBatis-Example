package com.yuxeng.display.usermodel.Email;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@Data：自动添加getter和setter
@AllArgsConstructor：为所有字段设置全参数构造函数
@NoArgsConstructor：为所有字段设置无参数构造函数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
  private List<String> tos;
  private String subject;
  private String content;
}
