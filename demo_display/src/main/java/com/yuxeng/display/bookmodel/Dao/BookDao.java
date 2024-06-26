package com.yuxeng.display.bookmodel.Dao;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.util.PageBean;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookDao {

  List<Book> selectBookByKeyword(String keyword);

  /**
   * 根据查询参数获取符合条件的书籍数量
   *
   * @param paramMap 包含查询条件的参数映射，键为参数名，值为参数值
   * @return 符合条件的书籍总数
   */
  Integer selectBookCount(Map<String, Object> paramMap);

  /**
   * 根据查询参数获取所有符合条件的书籍
   *
   * @param paramMap 包含查询条件的参数映射，键为参数名，值为参数值
   * @return 包含所有符合条件书籍的列表
   */
  List<Book> selectBook(Map<String,Object> paramMap);

  /**
   * 根据书籍ID获取书籍详情
   *
   * @param bookId 书籍ID
   * @return 书籍详情
   */
  Book selectById(int bookId);

  /**
   * 删除书籍
   *
   * @param bookId 书籍id
   */
  void deleteBook(int bookId);

  /**
   * 插入新书籍
   *
   * @param book 要插入的Book
   */
  void insertBook(Book book);

  /**
   * 更新书籍信息
   *
   * @param book 要更新的Book对象
   */
  void updateBook(Book book);

  /**
   * 给用户推荐书籍
   * @param userId 用户id
   * @return 给用户推荐的书籍的列表，按照分页显示
   */
  //
  List<Book> recommendBook(int userId,@Param("startIndex")int startIndex,@Param("pageSize")int pageSize);
}

