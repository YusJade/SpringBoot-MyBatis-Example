package com.yuxeng.display.bookmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  private BookDao bookDao;

  public int matchBook(String keyword) {
    return bookDao.selectBookByKeyword_int(keyword);
  }

  public List<Book> queryBook(String keyword) {
    return bookDao.selectBookByKeyword(keyword);
  }

  public List<Book> getAllBooks() {
    return bookDao.selectAllBooks();
  }

  public boolean deleteBook(Integer id) {
    return bookDao.deleteBook(id) > 0;
  }

  public boolean addBook (Book book) {return bookDao.insertBook(book);}

  public Book getBook(Integer id) {
    return bookDao.selectBookById(id);
  }

  public boolean editBook(Book book) {
    return bookDao.updateBook(book) > 0;
  }
}
