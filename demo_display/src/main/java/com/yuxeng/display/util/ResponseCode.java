package com.yuxeng.display.util;

import lombok.Data;


public enum ResponseCode {
  ADMIN_NOT_EXIST(700),
  ADMIN_WRONG_PASSWORD(701);
  private final int value;
  ResponseCode(int i) {
    this.value = i;
  }
}
