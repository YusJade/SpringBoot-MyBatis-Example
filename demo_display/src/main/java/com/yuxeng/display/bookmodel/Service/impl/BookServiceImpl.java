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
  public PageBean<Book> listBooksByPage(List<String> params) {
    int pageOn = Integer.parseInt(params.get(0)); // 获取当前页码
    int pageSize = Integer.parseInt(params.get(1)); // 获取每页显示的数量

    PageBean<Book> pageBean = new PageBean<>(pageOn, pageSize);
    int startIndex = pageBean.getStartIndex(); // 计算起始索引

    // 构建参数Map
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", startIndex);
    for (int i = 2; i < params.size(); i++) {
      paramMap.put("param" + (i - 1), params.get(i)); // 参数名为param1, param2, ...
    }

    // 查询符合条件的图书列表
    List<Book> datas = bookDao.selectBook(paramMap);
    pageBean.setDatas(datas);

    // 查询符合条件的图书总数
    int totalRecords = bookDao.selectBookCount(paramMap);
    pageBean.setTotalSize(totalRecords);

    return pageBean;
  }

  @Override
  public List<BookCategory> listCategory() {
    return bookDao.selectBookByCategory();
  }

  @Override
  public void saveBook(Map<String, Object> bookMap) {
    // 从bookMap中获取数据并创建Book对象
    Book book = new Book();
    book.setTitle((String) bookMap.get("title"));
    book.setAuthor((String) bookMap.get("author"));
    book.setCategory_id((Integer) bookMap.get("category_id"));
    book.setPublisher((String) bookMap.get("publisher"));
    book.setQuantity((Integer) bookMap.get("quantity"));
    bookDao.insertBook(bookMap);
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
  public PageBean<Book> recommendBook(int userId){
    return bookDao.recommendBook(userId);
  }

}
