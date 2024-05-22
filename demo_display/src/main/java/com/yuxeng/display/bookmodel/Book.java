package com.yuxeng.display.bookmodel;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Book {

  private Integer id;
  private String tittle;
  private String author;
  private Integer category_id;
  private String publisher;
  private Integer quantity;
  private Timestamp created_at;
}
