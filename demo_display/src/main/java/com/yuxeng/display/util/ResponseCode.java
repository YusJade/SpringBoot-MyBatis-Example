package com.yuxeng.display.util;

import lombok.Getter;


@Getter
public enum ResponseCode {

  BAD_REQUEST(400),USERNAME_REPEATED(401),
  SUCCESS(500), FAILED(501), OUT_TIME(502),
  LOGIN_SUCCESS(700), ACCOUNT_NOT_EXIST(701), WRONG_PASSWORD(702);

  private final int value;

  ResponseCode(int i) {
    this.value = i;
  }
}
