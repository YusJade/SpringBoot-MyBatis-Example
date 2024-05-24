package com.yuxeng.display.bookmodel;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Book_Category {
  private long id;
  private String name;
  private Integer quantity;
  private Timestamp created_at;

}
