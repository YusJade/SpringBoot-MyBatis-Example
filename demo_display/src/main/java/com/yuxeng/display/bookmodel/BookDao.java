package com.yuxeng.display.bookmodel;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookDao {

  /**
   * 显示id对应书籍
   * @param id 书籍id
   * @return id对应书籍
   */
  Book selectBookById(@Param("id") Integer id);

  /**
   * 显示所有书籍
   * @return 包含所有书籍的列表
   */
  List<Book> selectAllBooks();

  /**
   * 显示符合关键词的书籍的数目
   * @param keyword 关键词
   * @return 符合关键词书籍的数目
   */
  Integer selectBookByKeyword_int(@Param("keyword") String keyword);

  /**
   * 显示符合关键词的书籍
   * @param keyword 关键词
   * @return 符合关键词书籍的列表
   */
  List<Book> selectBookByKeyword(@Param("keyword") String keyword);

  /**
   * 显示对应分类书籍的数目
   * @param category_id Book_Category的分类id
   * @return 对应分类书籍的数目
   */
  Integer selectBookByCategory_int(Book_Category category_id);

  /**
   * 显示对应分类的全部书籍
   *
   * @param category_id Book类的分类id
   * @return 对应分类的全部书籍的列表
   */
  List<Book> selectBookByCategory(Book category_id);

  /**
   * 显示所有的分类
   * @return 分类的列表
   */
  List<Book_Category> selectAllCategory();

  /**
   * 删除书籍
   *
   * @param id 书籍id
   * @return 被删除的书籍数量（一般为1，如果删除失败则为0）
   */
  Integer deleteBook(@Param("id") Integer id);

  /**
   * 插入新书籍
   *
   * @param book 要插入的Book对象
   * @return 插入操作是否成功
   */
  boolean insertBook(Book book);

  /**
   * 更新书籍信息
   *
   * @param book 要更新的Book对象
   * @return 被更新的书籍数量（一般为1，如果更新失败则为0）
   */
  Integer updateBook(Book book);
}

