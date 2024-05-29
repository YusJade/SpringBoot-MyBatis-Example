package com.yuxeng.display.bookmodel.Service.impl;

import com.yuxeng.display.bookmodel.Dao.BookDao;
import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.usermodel.UserController;
import com.yuxeng.display.util.PageBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.yuxeng.display.bookmodel.Service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

  @Autowired
  private BookDao bookDao;
  @Autowired
  private UserController user;


  @Override
  public PageBean<Book> listBooksByPage(String title, String author, Integer categoryId,
      String publisher, int startPage, int pageSize) {
    Map<String,Object> map = new HashMap<>();
    map.put("title",title);
    map.put("author",author);
    map.put("category_id",categoryId);
    map.put("publisher",publisher);

    PageBean<Book> bean = new PageBean<>(startPage,pageSize);

    bean.setTotalSize(bookDao.selectBookCount(map));
    bean.setTotalPage(bookDao.selectBookCount(map)/pageSize);
    bean.setPageSize(pageSize);
    bean.setPageOn(startPage);

    int startIndex = (startPage - 1) * pageSize;
    map.put("startIndex",startIndex);
    map.put("pageSize",pageSize);
    bean.setDatas(bookDao.selectBook(map));
    return bean;
  }

  @Override
  public int saveBook(Map<String, Object> bookMap) {
    Book book = new Book();
    book.setTitle((String) bookMap.get("title"));
    book.setAuthor((String) bookMap.get("author"));
    book.setCategory_id((Integer) bookMap.get("category_id"));
    book.setPublisher((String) bookMap.get("publisher"));
    book.setQuantity((Integer) bookMap.get("quantity"));
    bookDao.insertBook(book);
    return book.getId();
  }

  @Override
  public Book getBookById(int bookId) {
    return bookDao.selectById(bookId);
  }

  @Override
  public void updateBook(Book book) {
    bookDao.updateBook(book);
  }

  @Override
  public void removeBook(int bookId) {
  bookDao.deleteBook(bookId);
  }

  @Override
  public PageBean<Book> recommendBook(int userId,int startPage,int pageSize){
    PageBean<Book> bean = new PageBean<>(startPage,pageSize);
    bean.setTotalSize(bookDao.recommendBook(userId,startPage,pageSize).size());
    bean.setTotalPage((int) Math.ceil((double) bean.getTotalSize() /pageSize));
    bean.setPageSize(pageSize);
    bean.setPageOn(startPage);
    int startIndex = (startPage - 1) * 10;
    bean.setDatas(bookDao.recommendBook(userId,startIndex,pageSize));

    return bean;
  }

}
