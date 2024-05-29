package com.yuxeng.display.bookmodel.Service.impl;

import com.yuxeng.display.bookmodel.Dao.BookCategoryDao;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.BookCategoryService;
import com.yuxeng.display.util.PageBean;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

  @Autowired
  private BookCategoryDao bookCategoryDao;

  @Override
  public PageBean<BookCategory> listCategoryByPage(int startPage,int pageSize) {
    PageBean<BookCategory> bean = new PageBean<>(startPage, pageSize);

    bean.setTotalSize(bookCategoryDao.countBookCategories());
    bean.setTotalPage((int)Math.ceil((double) bookCategoryDao.countBookCategories() /pageSize) );
    bean.setPageSize(pageSize);
    bean.setPageOn(startPage);

    int startIndex = (startPage-1) * pageSize;
    bean.setDatas(bookCategoryDao.selectCategory(startIndex,pageSize));
    return bean;
  }

  @Override
  public BookCategory getCategoryById(int categoryId){
    return bookCategoryDao.selectCategoryById(categoryId);
  }

  @Override
  public BookCategory getCategoryByName(String categoryName) {
    return bookCategoryDao.selectCategoryByName(categoryName);
  }

  @Override
  public void updateBookCategory(int id,String categoryName) {
    bookCategoryDao.updateBookCategory(id,categoryName);
  }

  @Override
  public void removeBookCategory(int categoryId) {
    bookCategoryDao.deleteBookCategory(categoryId);
  }

  @Override
  public void saveBookCategory(String categoryName) {
    bookCategoryDao.insertBookCategory(categoryName);
  }
}
