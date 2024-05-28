package com.yuxeng.display.util;

import lombok.Getter;


@Getter
public enum ResponseCode {

  SUCCESS(500), FAILED(501),
  BOOK_NOT_EXIST(601), BOOK_UNAVAILABLE(602),
  BORROW_LIMITED(603), BORROW_ALREADY(604),
  BORROW_NOT_EXIST(605), RETURN_OVERDUE(606), RETURN_ALREADY(607),
  LOGIN_SUCCESS(700), ACCOUNT_NOT_EXIST(701), WRONG_PASSWORD(702),
  CATEGORY_NOT_EXIST(801);


  private final int value;
  ResponseCode(int i) {
    this.value = i;
  }
}
