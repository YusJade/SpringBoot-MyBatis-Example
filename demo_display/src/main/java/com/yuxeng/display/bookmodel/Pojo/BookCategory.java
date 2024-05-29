package com.yuxeng.display.bookmodel.Pojo;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class BookCategory {
  private Integer id;
  private String name;
  private Integer quantity;
  private Timestamp created_at;

}
