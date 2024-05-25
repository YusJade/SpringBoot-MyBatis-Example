package com.yuxeng.display.bookmodel.Dao;

import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookCategoryDao {

  /**
   * 显示全部分类
   * @return 分类的列表
   */
  ArrayList<BookCategory> listCategory();

  /**
   * 更新书籍分类信息
   * @param category 要更新的Category对象
   */
  void updateBookCategory(BookCategory category);

  /**
   * 删除书籍分类
   * @param categoryId 要删除的分类ID
   */
  void deleteBookCategory(Integer categoryId);

  /**
   * 添加新书籍分类
   * @param categoryName 新分类的名称
   */
  void addBookCategory(String categoryName);
}

