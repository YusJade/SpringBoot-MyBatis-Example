package com.yuxeng.display.bookmodel.Dao;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.util.PageBean;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookCategoryDao {

  /**
   * 显示全部分类
   * @return 分类的列表
   */
  List<BookCategory> selectCategory(
      @Param("startIndex") int startIndex,
      @Param("pageSize") int pageSize);

  /**
   * 通过id寻找对应列表
   * @param id 类别id
   * @return 对应类别
   */
  BookCategory selectCategoryById(int id);

  /**
   * 获取图书类别数量
   * @return 类别数量
   */
  int countBookCategories ();

  /**
   * 更新书籍分类信息
   * @param categoryName 更新后的类别名
   */
  void updateBookCategory(int id,@Param("categoryName")String categoryName);

  /**
   * 删除书籍分类
   * @param categoryId 要删除的分类ID
   */
  void deleteBookCategory(int categoryId);

  /**
   * 添加新书籍分类
   * @param categoryName 新分类的名称
   */
  void insertBookCategory(String categoryName);
}

