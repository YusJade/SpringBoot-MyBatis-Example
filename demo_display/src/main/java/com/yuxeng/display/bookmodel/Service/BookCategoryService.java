package com.yuxeng.display.bookmodel.Service;

import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import java.util.ArrayList;

public interface BookCategoryService {
  ArrayList<BookCategory> listCategory();

  void updateBookCategory(BookCategory category);

  void delBookCategory(Integer categoryId);

  void addBookCategory(String categoryName);
}
