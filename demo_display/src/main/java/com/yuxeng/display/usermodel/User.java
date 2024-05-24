package com.yuxeng.display.usermodel;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;
import java.sql.Timestamp;
import java.util.Date;


@Data
public class User {

  private Long id;  // PK & AutoIncreament
  private String username;  // Unique
  private String password;
  private String name;
  private String gender;
  private String phone;  // Unique
  private String email;  // Unique
  private Long max_borrow_days;
  private Long max_borrow_books;
  private Timestamp created_at;
}
