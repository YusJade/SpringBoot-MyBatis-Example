package com.yuxeng.display.util;

import lombok.Getter;

@Getter
public class Responses<T> {

  private Integer code;
  private String msg;
  private T result;

  public Responses(ResponseCode code, String msg, T result) {
    this.code = code.getValue();
    this.msg = msg;
    this.result = result;
  }
}
