package com.yuxeng.display.util;

import lombok.Getter;


@Getter
public enum ResponseCode {

  SUCCESS(500), FAILED(501),
  LOGIN_SUCCESS(700), ACCOUNT_NOT_EXIST(701), WRONG_PASSWORD(702);

  private final int value;
  ResponseCode(int i) {
    this.value = i;
  }
}
