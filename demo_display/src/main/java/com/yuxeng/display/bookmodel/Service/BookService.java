package com.yuxeng.display.bookmodel.Service;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.util.PageBean;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;

import java.util.List;
import java.util.Map;


public interface BookService {

  PageBean<Book> getListBooksByPage (Map<String, Object> paramMap);

  List<BookCategory> getListCategory();

  void saveBook(Book book);

  Book selectById(int bookId);

  void updateBook(Book book);

  void removeBook(int bookId);
}
