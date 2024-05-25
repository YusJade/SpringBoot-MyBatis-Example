package com.yuxeng.display.bookmodel.Service.impl;

import com.yuxeng.display.bookmodel.Dao.BookCategoryDao;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.BookCategoryService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

  @Autowired
  private BookCategoryDao bookCategoryDao;

  @Override
  public ArrayList<BookCategory> listCategory() {
    return bookCategoryDao.listCategory();
  }

  @Override
  public void updateBookCategory(BookCategory category) {
    bookCategoryDao.updateBookCategory(category);
  }

  @Override
  public void delBookCategory(Integer categoryId) {
    bookCategoryDao.deleteBookCategory(categoryId);
  }

  @Override
  public void addBookCategory(String categoryName) {
    bookCategoryDao.addBookCategory(categoryName);
  }
}
