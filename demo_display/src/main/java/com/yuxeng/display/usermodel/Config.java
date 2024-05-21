package com.yuxeng.display.usermodel;

import org.springframework.stereotype.Service;

@Service
public class Config {
  // 默认最大借阅日和借阅书数量
  Long max_borrow_days = 30L;
  Long max_borrow_books = 10L;
}
