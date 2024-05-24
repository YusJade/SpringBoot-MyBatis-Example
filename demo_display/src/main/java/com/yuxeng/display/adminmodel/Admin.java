package com.yuxeng.display.adminmodel;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Admin {

  private int id;
  private String username;
  private String password;
  private String name;
  private String phone;
  private String email;
  private Timestamp created_at;
}
