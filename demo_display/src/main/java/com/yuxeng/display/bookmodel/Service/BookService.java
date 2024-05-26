package com.yuxeng.display.bookmodel.Service;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.util.PageBean;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;

import java.util.List;
import java.util.Map;

/**
 * 用于管理图书的服务接口。
 */
public interface BookService {

  /**
   * 根据提供的参数获取分页的图书列表。
   *
   * @param paramMap 分页和过滤的参数。
   * @return 分页的图书列表。
   */
  PageBean<Book> listBooksByPage(Map<String, Object> paramMap);

  /**
   * 获取所有图书类别的列表。
   *
   * @return 图书类别的列表。
   */
  List<BookCategory> listCategory();

  /**
   * 保存新书。
   *
   * @param book 要保存的图书对象。
   */
  void saveBook(Book book);

  /**
   * 根据图书ID检索图书。
   *
   * @param bookId 要检索的图书ID。
   * @return 对应于提供的ID的图书。
   */
  Book getBookById(int bookId);

  /**
   * 更新现有图书。
   *
   * @param book 更新后的图书对象。
   */
  void updateBook(Book book);

  /**
   * 从数据库中移除图书。
   *
   * @param bookId 要移除的图书ID。
   */
  void removeBook(int bookId);
}
