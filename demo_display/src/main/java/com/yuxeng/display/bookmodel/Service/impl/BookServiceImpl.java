package com.yuxeng.display.bookmodel.Service.impl;

import com.yuxeng.display.bookmodel.Dao.BookDao;
import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.util.PageBean;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.yuxeng.display.bookmodel.Service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

  @Autowired
  private BookDao bookDao;

  @Override
  public PageBean<Book> listBooksByPage(Map<String,Object> paramMap){
    PageBean<Book> pageBean = new PageBean<Book>(
        (Integer) paramMap.get("pageOn"),
        (Integer) paramMap.get("pageSize")
    );
    Integer startIndex = pageBean.getStartIndex();
    paramMap.put("startIndex",startIndex);

    List<Book> datas = bookDao.selectBook(paramMap);
    pageBean.setDatas(datas);

    Integer totalRecords = bookDao.selectBookCount(paramMap);
    pageBean.setTotalSize(totalRecords);
    return pageBean;

  }

  @Override
  public List<BookCategory> listByCategory() {
    return bookDao.selectBookByCategory();
  }

  @Override
  public void saveBook(Book book) {
    bookDao.insertBook(book);
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


}
