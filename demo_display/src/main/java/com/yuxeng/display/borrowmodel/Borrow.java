package com.yuxeng.display.borrowmodel;

import java.sql.Timestamp;
import lombok.Data;

/*
CREATE TABLE `tb_borrow` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `user_id` bigint,
  `book_id` bigint,
  `borrow_date` datetime DEFAULT (now()),
  `return_date` datetime,
  `really_return_date` datetime
);
 */
@Data
public class Borrow {

  private int id;
  private int user_id;
  private int book_id;
  private Timestamp borrow_date;
  private Timestamp return_date;
  private Timestamp really_return_date;
}
